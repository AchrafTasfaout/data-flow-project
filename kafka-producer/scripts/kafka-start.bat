@echo off
echo Starting Kafka broker...
docker rm -f fraud-kafka-broker
docker network create fraud-detection-net 
docker run -it --rm --name fraud-kafka-broker --network fraud-detection-net --user root -p 9000:9000 -p 9092:9092 ghcr.io/osekoo/kafka:3.5
echo Kafka broker started successfully.