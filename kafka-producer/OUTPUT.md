# Sample Output of Kafka Producer

## Kafka Docker Container Logs

```
2025-07-19 14:23:37.474  INFO 474 [kground-preinit] o.h.v.i.u.Version                        : HV000001: Hibernate Validator 8.0.1.Final
2025-07-19 14:23:37.556  INFO 474 [           main] o.s.b.StartupInfoLogger                  : Starting Kafdrop v4.1.0 using Java 17.0.13 with PID 474 (/opt/kafdrop.jar started by root in /opt/kafka_2.13-2.4.0)
2025-07-19 14:23:37.575  INFO 474 [           main] o.s.b.SpringApplication                  : No active profile set, falling back to 1 default profile: "default"
2025-07-19 14:23:41.433  INFO 474 [           main] i.u.s.s.ServletContextImpl               : Initializing Spring embedded WebApplicationContext
2025-07-19 14:23:41.436  INFO 474 [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 3766 ms
2025-07-19 14:23:41.938  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking truststore file kafka.truststore.jks
2025-07-19 14:23:41.939  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking keystore file kafka.keystore.jks
2025-07-19 14:23:41.939  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking properties file kafka.properties
2025-07-19 14:23:42.265  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking truststore file kafka.truststore.jks
2025-07-19 14:23:42.265  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking keystore file kafka.keystore.jks
2025-07-19 14:23:42.266  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking properties file kafka.properties
2025-07-19 14:23:42.325  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking truststore file kafka.truststore.jks
2025-07-19 14:23:42.326  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking keystore file kafka.keystore.jks
2025-07-19 14:23:42.328  INFO 474 [           main] k.c.KafkaConfiguration                   : Checking properties file kafka.properties
2025-07-19 14:23:42.417  INFO 474 [           main] k.s.BuildInfo                            : Kafdrop version: 4.1.0, build time: 2024-12-10T08:39:49.205Z
2025-07-19 14:23:44.102  INFO 474 [           main] o.s.b.a.e.w.EndpointLinksResolver        : Exposing 14 endpoints beneath base path '/actuator'
2025-07-19 14:23:44.405  INFO 474 [           main] i.u.Undertow                             : starting server: Undertow - 2.3.18.Final
2025-07-19 14:23:44.454  INFO 474 [           main] o.x.Xnio                                 : XNIO version 3.8.16.Final
2025-07-19 14:23:44.485  INFO 474 [           main] o.x.n.NioXnio                            : XNIO NIO Implementation Version 3.8.16.Final
2025-07-19 14:23:44.556  INFO 474 [           main] o.j.t.Version                            : JBoss Threads version 3.5.0.Final
2025-07-19 14:23:44.624  INFO 474 [           main] o.s.b.w.e.u.UndertowWebServer            : Undertow started on port 9000 (http) with context path '/'
2025-07-19 14:23:44.670  INFO 474 [           main] o.s.b.StartupInfoLogger                  : Started Kafdrop in 7.97 seconds (process running for 9.959)
[2025-07-19 14:24:07,573] INFO Creating topic transactions with configuration {} and initial partition assignment HashMap(0 -> ArrayBuffer(1001), 1 -> ArrayBuffer(1001), 2 -> ArrayBuffer(1001)) (kafka.zk.AdminZkClient)
[2025-07-19 14:24:07,607] INFO [KafkaApi-1001] Auto creation of topic transactions with 3 partitions and replication factor 1 is successful (kafka.server.KafkaApis)
[2025-07-19 14:24:07,746] INFO [ReplicaFetcherManager on broker 1001] Removed fetcher for partitions Set(transactions-1, transactions-0, transactions-2) (kafka.server.ReplicaFetcherManager)
[2025-07-19 14:24:07,842] INFO [Log partition=transactions-1, dir=/opt/kafka/data] Loading producer state till offset 0 with message format version 2 (kafka.log.Log)
[2025-07-19 14:24:07,851] INFO [Log partition=transactions-1, dir=/opt/kafka/data] Completed load of log with 1 segments, log start offset 0 and log end offset 0 in 59 ms (kafka.log.Log)
[2025-07-19 14:24:07,858] INFO Created log for partition transactions-1 in /opt/kafka/data/transactions-1 with properties {compression.type -> producer, min.insync.replicas -> 1, message.downconversion.enable -> true, segment.jitter.ms -> 0, cleanup.policy -> [delete], flush.ms -> 1000, retention.ms -> 3600000, segment.bytes -> 1073741824, flush.messages -> 1, message.format.version -> 2.4-IV1, max.compaction.lag.ms -> 9223372036854775807, file.delete.delay.ms -> 60000, max.message.bytes -> 10485760, min.compaction.lag.ms -> 0, message.timestamp.type -> CreateTime, preallocate -> false, index.interval.bytes -> 4096, min.cleanable.dirty.ratio -> 0.5, unclean.leader.election.enable -> false, retention.bytes -> -1, delete.retention.ms -> 86400000, segment.ms -> 604800000, message.timestamp.difference.max.ms -> 9223372036854775807, segment.index.bytes -> 10485760}. (kafka.log.LogManager)
[2025-07-19 14:24:07,860] INFO [Partition transactions-1 broker=1001] No checkpointed highwatermark is found for partition transactions-1 (kafka.cluster.Partition)
[2025-07-19 14:24:07,862] INFO [Partition transactions-1 broker=1001] Log loaded for partition transactions-1 with initial high watermark 0 (kafka.cluster.Partition)
[2025-07-19 14:24:07,864] INFO [Partition transactions-1 broker=1001] transactions-1 starts at Leader Epoch 0 from offset 0. Previous Leader Epoch was: -1 (kafka.cluster.Partition)
[2025-07-19 14:24:07,893] INFO [Log partition=transactions-0, dir=/opt/kafka/data] Loading producer state till offset 0 with message format version 2 (kafka.log.Log)
[2025-07-19 14:24:07,894] INFO [Log partition=transactions-0, dir=/opt/kafka/data] Completed load of log with 1 segments, log start offset 0 and log end offset 0 in 3 ms (kafka.log.Log)
[2025-07-19 14:24:07,895] INFO Created log for partition transactions-0 in /opt/kafka/data/transactions-0 with properties {compression.type -> producer, min.insync.replicas -> 1, message.downconversion.enable -> true, segment.jitter.ms -> 0, cleanup.policy -> [delete], flush.ms -> 1000, retention.ms -> 3600000, segment.bytes -> 1073741824, flush.messages -> 1, message.format.version -> 2.4-IV1, max.compaction.lag.ms -> 9223372036854775807, file.delete.delay.ms -> 60000, max.message.bytes -> 10485760, min.compaction.lag.ms -> 0, message.timestamp.type -> CreateTime, preallocate -> false, index.interval.bytes -> 4096, min.cleanable.dirty.ratio -> 0.5, unclean.leader.election.enable -> false, retention.bytes -> -1, delete.retention.ms -> 86400000, segment.ms -> 604800000, message.timestamp.difference.max.ms -> 9223372036854775807, segment.index.bytes -> 10485760}. (kafka.log.LogManager)
[2025-07-19 14:24:07,895] INFO [Partition transactions-0 broker=1001] No checkpointed highwatermark is found for partition transactions-0 (kafka.cluster.Partition)
[2025-07-19 14:24:07,895] INFO [Partition transactions-0 broker=1001] Log loaded for partition transactions-0 with initial high watermark 0 (kafka.cluster.Partition)
[2025-07-19 14:24:07,895] INFO [Partition transactions-0 broker=1001] transactions-0 starts at Leader Epoch 0 from offset 0. Previous Leader Epoch was: -1 (kafka.cluster.Partition)
[2025-07-19 14:24:07,910] INFO [Log partition=transactions-2, dir=/opt/kafka/data] Loading producer state till offset 0 with message format version 2 (kafka.log.Log)
[2025-07-19 14:24:07,911] INFO [Log partition=transactions-2, dir=/opt/kafka/data] Completed load of log with 1 segments, log start offset 0 and log end offset 0 in 3 ms (kafka.log.Log)
[2025-07-19 14:24:07,912] INFO Created log for partition transactions-2 in /opt/kafka/data/transactions-2 with properties {compression.type -> producer, min.insync.replicas -> 1, message.downconversion.enable -> true, segment.jitter.ms -> 0, cleanup.policy -> [delete], flush.ms -> 1000, retention.ms -> 3600000, segment.bytes -> 1073741824, flush.messages -> 1, message.format.version -> 2.4-IV1, max.compaction.lag.ms -> 9223372036854775807, file.delete.delay.ms -> 60000, max.message.bytes -> 10485760, min.compaction.lag.ms -> 0, message.timestamp.type -> CreateTime, preallocate -> false, index.interval.bytes -> 4096, min.cleanable.dirty.ratio -> 0.5, unclean.leader.election.enable -> false, retention.bytes -> -1, delete.retention.ms -> 86400000, segment.ms -> 604800000, message.timestamp.difference.max.ms -> 9223372036854775807, segment.index.bytes -> 10485760}. (kafka.log.LogManager)
[2025-07-19 14:24:07,914] INFO [Partition transactions-2 broker=1001] No checkpointed highwatermark is found for partition transactions-2 (kafka.cluster.Partition)
[2025-07-19 14:24:07,914] INFO [Partition transactions-2 broker=1001] Log loaded for partition transactions-2 with initial high watermark 0 (kafka.cluster.Partition)
[2025-07-19 14:24:07,914] INFO [Partition transactions-2 broker=1001] transactions-2 starts at Leader Epoch 0 from offset 0. Previous Leader Epoch was: -1 (kafka.cluster.Partition)
```

## Producer Output

```
Produced 7950 transactions
```
