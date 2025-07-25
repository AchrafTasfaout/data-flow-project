# Spark Structured Streaming Fraud Detection

## Overview

This application consumes simulated payment transactions from Kafka, detects potentially fraudulent patterns in real time using Spark Structured Streaming, and outputs suspicious events to the console, Parquet files, and a Kafka topic.

## Setup & Usage

### 1. Package the Application

From the project root, build the JAR using SBT:

```
sbt package
```

### 2. Start the Spark Environment

Start the Spark environment using the provided script:

- **On Linux/macOS:**
  ```
  bash spark-env.sh
  ```
- **On Windows:**
  ```
  spark-env.bat
  ```

This will launch a Spark cluster with the necessary network configuration.

### 3. Run the Streaming Application

From within the Spark environment, start the streaming job:

```
bash run-app.sh
```

This script will submit the packaged application to the Spark cluster.

## Outputs

- **Console:** Suspicious transactions are printed to the console for monitoring.
- **Parquet:** Fraudulent events are saved to the `output/` directory in Parquet format, grouped by fraud type.
- **Kafka:** All detected frauds are sent to the `fraud-alerts` Kafka topic.

## Fraud Detection Rules

The application detects:

- High-value transactions (amount > 1000)
- More than 3 transactions from the same user in under 1 minute
- Transactions from the same user in multiple countries within 5 minutes
- Multiple payment methods used by the same user within 10 minutes

## Requirements

- Docker (for Spark environment)
- Kafka running and accessible at `localhost:9092`
- SBT (for packaging)

---

For more details, see the code in [`src/main/scala/FraudDetectionStreaming.scala`](src/main/scala/FraudDetectionStreaming.scala).
