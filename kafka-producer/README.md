# Kafka Producer for Real-Time Fraud Detection

## Overview

This application generates and streams simulated payment transaction events to a Kafka topic. It is designed to provide real-time data for downstream analytics and fraud detection pipelines.

## Setup & Usage

### 1. Install Dependencies

From the project root, install the required Python packages:

```
pip install -r requirements.txt
```

### 2. Start Kafka Broker

Start a local Kafka broker using the provided script:

- **On Linux/macOS:**
  ```
  bash scripts/kafka-start.sh
  ```
- **On Windows:**
  ```
  scripts\kafka-start.bat
  ```

This will launch a Kafka broker in Docker, ready to receive events.

### 3. Run the Producer

Start the Python producer to generate and send transactions:

```
python src/producer.py
```

The producer will continuously send simulated transactions to the `transactions` Kafka topic at a configurable rate.

### 4. Stop Kafka Broker

When finished, stop the Kafka broker:

- **On Linux/macOS:**
  ```
  bash scripts/kafka-stop.sh
  ```
- **On Windows:**
  ```
  scripts\kafka-stop.bat
  ```

## Data Format — Transaction Event (JSON)

Each event sent to Kafka is a JSON object with the following fields:

- `user_id`: Unique user identifier
- `transaction_id`: Unique transaction identifier
- `amount`: Transaction amount (float)
- `currency`: Transaction currency (e.g., EUR, USD, GBP)
- `timestamp`: ISO 8601 timestamp of the transaction
- `location`: City of the transaction
- `method`: Payment method (e.g., credit_card, debit_card, paypal, crypto)

**Example:**

```json
{
  "user_id": "u1234",
  "transaction_id": "t-0000001",
  "amount": 250.75,
  "currency": "USD",
  "timestamp": "2025-07-18T12:34:56Z",
  "location": "Berlin",
  "method": "credit_card"
}
```

## Configuration

- **Kafka Broker:** By default, the producer connects to `localhost:9092`.
- **Topic:** Events are sent to the `transactions` topic.
- **Rate:** The number of transactions per second can be adjusted in `src/producer.py` (`TRANSACTIONS_PER_SEC`).

## Requirements

- Python 3.7+
- Docker (for Kafka broker)
- Kafka (runs in Docker via provided scripts)

---

For more details, see the code in [`src/producer.py`](src/producer.py)
