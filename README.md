# Real-Time Fraud Detection Project

## Overview

This repository contains a complete real-time fraud detection pipeline, including:

- **Kafka Producer**: Generates and streams simulated payment transactions.
- **Spark Structured Streaming**: Consumes transactions, detects fraud patterns (using 4 different rules), and outputs alerts.
- **Streamlit Dashboard**: Visualizes real-time fraud alerts from Kafka.

Each component is organized in its own directory with dedicated documentation and sample outputs.

---

## Directory Structure

- [`kafka-producer/`](kafka-producer/): Python application to generate and send simulated transactions to Kafka.  
  See [`kafka-producer/README.md`](kafka-producer/README.md) for setup and usage.

- [`fraud-detection-spark/`](fraud-detection-spark/): Spark Structured Streaming application that reads transactions from Kafka, applies fraud detection rules, and outputs results to Kafka, Parquet, and the console.  
  See [`fraud-detection-spark/README.md`](fraud-detection-spark/README.md) for setup and usage.

- [`streamlit-dashboard/`](streamlit-dashboard/): Streamlit app that consumes fraud alerts from Kafka and displays them in a real-time dashboard.  
  See [`streamlit-dashboard/README.md`](streamlit-dashboard/README.md) for setup and usage.

---

## Getting Started

To run the full pipeline, follow these steps:

1. **Start Kafka and the Producer**

   - Go to [`kafka-producer/`](kafka-producer/) and follow the instructions in its [README](kafka-producer/README.md) to start Kafka and the transaction producer.

2. **Run the Spark Fraud Detection Application**

   - Go to [`fraud-detection-spark/`](fraud-detection-spark/) and follow the instructions in its [README](fraud-detection-spark/README.md) to build and run the Spark streaming job.

3. **Launch the Streamlit Dashboard**
   - Go to [`streamlit-dashboard/`](streamlit-dashboard/) and follow the instructions in its [README](streamlit-dashboard/README.md) to start the dashboard and view real-time fraud alerts.

---

## Sample Outputs

Each subfolder contains an `OUTPUT.md` file showing example outputs:

- **Kafka Producer**: Example logs and sample produced data ([`kafka-producer/OUTPUT.md`](kafka-producer/OUTPUT.md))
- **Spark Streaming**: Example console output and Parquet samples ([`fraud-detection-spark/OUTPUT.md`](fraud-detection-spark/OUTPUT.md))
- **Streamlit Dashboard**: Screenshots and a video recording of the dashboard in action ([`streamlit-dashboard/OUTPUT.md`](streamlit-dashboard/OUTPUT.md))

---

## Team Members

- Achraf Tasfaout
- Ahmed Mokeddem
- Mohamed Dhifallah
- Mouhamed Sy

---

For detailed information and configuration options, refer to the `README.md` in each
