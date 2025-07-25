import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object FraudDetectionStreaming {
  def main(args: Array[String]): Unit = {
    val kafkaBroker = "fraud-kafka-broker:9093"
    val transactionsTopic = "transactions"
    val fraudAlertsTopic = "fraud-alerts"

    val spark = SparkSession.builder
      .appName("Fraud Detection Streaming")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    // Schema for the JSON data
    val schema = new StructType()
      .add("user_id", StringType)
      .add("transaction_id", StringType)
      .add("amount", DoubleType)
      .add("currency", StringType)
      .add("timestamp", StringType)
      .add("location", StringType)
      .add("method", StringType)

    // Read from Kafka ONCE
    val transactions = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaBroker)
      .option("subscribe", transactionsTopic)
      .option("startingOffsets", "latest")
      .load()
      .selectExpr("CAST(value AS STRING) as json")
      .select(from_json(col("json"), schema).as("data"))
      .select("data.*")
      .withColumn("event_time", to_timestamp(col("timestamp")))

    // --- Console output queries ---
    // High value frauds (> $1000)
    val highValueFraudsConsole = transactions
      .filter(col("amount") > 1000)
      .withColumn("fraud_type", lit("HighValueFraud"))
    
    // Frequent user frauds (more than 3 transactions in 1 minute)
    val userTxnCountsConsole = transactions
      .withWatermark("event_time", "2 minutes")
      .groupBy(window(col("event_time"), "1 minute"), col("user_id"))
      .count()
      .filter(col("count") > 3)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("count").alias("txn_count"))
      .withColumn("fraud_type", lit("FrequentUserFraud"))

    // Multi-country frauds (more than 1 country in 5 minutes)
    val userCountryCountsConsole = transactions
      .withWatermark("event_time", "10 minutes")
      .groupBy(window(col("event_time"), "5 minutes"), col("user_id"))
      .agg(approx_count_distinct(col("location")).alias("country_count"))
      .filter(col("country_count") > 1)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("country_count"))
      .withColumn("fraud_type", lit("MultiCountryFraud"))

    // Multi-method frauds (more than 1 method in 10 minutes)
    val userMethodCountsConsole = transactions
      .withWatermark("event_time", "20 minutes")
      .groupBy(window(col("event_time"), "10 minutes"), col("user_id"))
      .agg(approx_count_distinct(col("method")).alias("method_count"))
      .filter(col("method_count") > 1)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("method_count"))
      .withColumn("fraud_type", lit("MultiMethodFraud"))

    highValueFraudsConsole.writeStream
      .outputMode("append")
      .format("console")
      .option("truncate", false)
      .queryName("HighValueFrauds")
      .option("checkpointLocation", "/tmp/spark-checkpoint-console-highvalue")
      .start()

    userTxnCountsConsole.writeStream
      .outputMode("append")
      .format("console")
      .option("truncate", false)
      .queryName("FrequentUserFrauds")
      .option("checkpointLocation", "/tmp/spark-checkpoint-console-userTxnCounts")
      .start()

    userCountryCountsConsole.writeStream
      .outputMode("append")
      .format("console")
      .option("truncate", false)
      .queryName("MultiCountryFrauds")
      .option("checkpointLocation", "/tmp/spark-checkpoint-console-userCountryCounts")
      .start()

    userMethodCountsConsole.writeStream
      .outputMode("append")
      .format("console")
      .option("truncate", false)
      .queryName("MultiMethodFrauds")
      .option("checkpointLocation", "/tmp/spark-checkpoint-console-userMethodCounts")
      .start()

    // --- Kafka output queries ---
    // High value frauds (> $1000)
    val highValueFraudsKafka = transactions.filter(col("amount") > 1000).withColumn("fraud_type", lit("HighValueFraud"))

    // Frequent user frauds (more than 3 transactions in 1 minute)  
    val userTxnCountsKafka = transactions
      .withWatermark("event_time", "2 minutes")
      .groupBy(window(col("event_time"), "1 minute"), col("user_id"))
      .count()
      .filter(col("count") > 3)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("count").alias("txn_count"))
      .withColumn("fraud_type", lit("FrequentUserFraud"))

    // Multi-country frauds (more than 1 country in 5 minutes)
    val userCountryCountsKafka = transactions
      .withWatermark("event_time", "10 minutes")
      .groupBy(window(col("event_time"), "5 minutes"), col("user_id"))
      .agg(approx_count_distinct(col("location")).alias("country_count"))
      .filter(col("country_count") > 1)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("country_count"))
      .withColumn("fraud_type", lit("MultiCountryFraud"))

    // Multi-method frauds (more than 1 method in 10 minutes)
    val userMethodCountsKafka = transactions
      .withWatermark("event_time", "20 minutes")
      .groupBy(window(col("event_time"), "10 minutes"), col("user_id"))
      .agg(approx_count_distinct(col("method")).alias("method_count"))
      .filter(col("method_count") > 1)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("method_count"))
      .withColumn("fraud_type", lit("MultiMethodFraud"))

    highValueFraudsKafka
      .selectExpr("to_json(struct(*)) AS value")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaBroker)
      .option("topic", fraudAlertsTopic)
      .option("checkpointLocation", "/tmp/spark-checkpoint-kafka-highvalue")
      .outputMode("append")
      .start()

    userTxnCountsKafka
      .selectExpr("to_json(struct(*)) AS value")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaBroker)
      .option("topic", fraudAlertsTopic)
      .option("checkpointLocation", "/tmp/spark-checkpoint-kafka-userTxnCounts")
      .outputMode("append")
      .start()

    userCountryCountsKafka
      .selectExpr("to_json(struct(*)) AS value")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaBroker)
      .option("topic", fraudAlertsTopic)
      .option("checkpointLocation", "/tmp/spark-checkpoint-kafka-userCountryCounts")
      .outputMode("append")
      .start()

    userMethodCountsKafka
      .selectExpr("to_json(struct(*)) AS value")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers", kafkaBroker)
      .option("topic", fraudAlertsTopic)
      .option("checkpointLocation", "/tmp/spark-checkpoint-kafka-userMethodCounts")
      .outputMode("append")
      .start()

    // --- Parquet output queries ---
    // High value frauds (> $1000)
    val highValueFraudsParquet = transactions.filter(col("amount") > 1000)
      .withColumn("fraud_type", lit("HighValueFraud"))

    // Frequent user frauds (more than 3 transactions in 1 minute)
    val userTxnCountsParquet = transactions
      .withWatermark("event_time", "2 minutes")
      .groupBy(window(col("event_time"), "1 minute"), col("user_id"))
      .count()
      .filter(col("count") > 3)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("count").alias("txn_count"))
      .withColumn("fraud_type", lit("FrequentUserFraud"))

    // Multi-country frauds (more than 1 country in 5 minutes)
    val userCountryCountsParquet = transactions
      .withWatermark("event_time", "10 minutes")
      .groupBy(window(col("event_time"), "5 minutes"), col("user_id"))
      .agg(approx_count_distinct(col("location")).alias("country_count"))
      .filter(col("country_count") > 1)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("country_count"))
      .withColumn("fraud_type", lit("MultiCountryFraud"))

    // Multi-method frauds (more than 1 method in 10 minutes)
    val userMethodCountsParquet = transactions
      .withWatermark("event_time", "20 minutes")
      .groupBy(window(col("event_time"), "10 minutes"), col("user_id"))
      .agg(approx_count_distinct(col("method")).alias("method_count"))
      .filter(col("method_count") > 1)
      .select(col("user_id"), col("window.start").alias("window_start"), col("window.end").alias("window_end"), col("method_count"))
      .withColumn("fraud_type", lit("MultiMethodFraud"))

    highValueFraudsParquet
      .writeStream
      .format("parquet")
      .option("path", "output/high_value_frauds")
      .option("checkpointLocation", "/tmp/spark-checkpoint-parquet-highvalue")
      .outputMode("append")
      .start()

    userTxnCountsParquet
      .writeStream
      .format("parquet")
      .option("path", "output/frequent_user_frauds")
      .option("checkpointLocation", "/tmp/spark-checkpoint-parquet-userTxnCounts")
      .outputMode("append")
      .start()

    userCountryCountsParquet
      .writeStream
      .format("parquet")
      .option("path", "output/multi_country_frauds")
      .option("checkpointLocation", "/tmp/spark-checkpoint-parquet-userCountryCounts")
      .outputMode("append")
      .start()

    userMethodCountsParquet
      .writeStream
      .format("parquet")
      .option("path", "output/multi_method_frauds")
      .option("checkpointLocation", "/tmp/spark-checkpoint-parquet-userMethodCounts")
      .outputMode("append")
      .start()

    // Wait for any query to terminate
    spark.streams.awaitAnyTermination()
  }
}