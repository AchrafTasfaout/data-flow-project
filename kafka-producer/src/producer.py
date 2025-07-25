import json
import time
from datetime import datetime
from faker import Faker
from kafka import KafkaProducer

from utils import generate_transaction

KAFKA_BROKER = "localhost:9092"
TOPIC = "transactions"
TRANSACTIONS_PER_SEC = 50

producer = KafkaProducer(
    bootstrap_servers=KAFKA_BROKER,
    value_serializer=lambda v: json.dumps(v).encode("utf-8"),
)

fake = Faker()
ref_start_time = datetime.utcnow()


def main():
    i = 0
    while True:
        for _ in range(TRANSACTIONS_PER_SEC):
            txn = generate_transaction(i, ref_start_time, fake)
            producer.send(TOPIC, txn)
            i += 1
        print(f"Produced {i} transactions", end="\r")
        time.sleep(1)


if __name__ == "__main__":
    main()
