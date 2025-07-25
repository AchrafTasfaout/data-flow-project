#!/bin/bash
spark-submit \
    --deploy-mode client \
    --master "spark://localhost:7077" \
    --executor-cores 1 \
    --executor-memory 2G \
    --num-executors 8 \
    --class "FraudDetectionStreaming" \
    "target/scala-2.12/fraud-detection-spark_2.12-0.1.jar" \
    1000000 \
