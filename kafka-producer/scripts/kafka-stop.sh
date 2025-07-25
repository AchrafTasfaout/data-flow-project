#!/bin/bash
echo "Stopping Kafka broker..."
docker rm -f fraud-kafka-broker
echo "Kafka broker stopped successfully."