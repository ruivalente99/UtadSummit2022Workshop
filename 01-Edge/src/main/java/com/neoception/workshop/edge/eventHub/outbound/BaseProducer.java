package com.neoception.workshop.edge.eventHub.outbound;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public abstract class BaseProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    protected void send(ProducerRecord<String, String> record) {
        try {
            kafkaTemplate.send(record);
            log.info("KAFKA | SENT | [topic={}, key={}, value={}]", record.topic(), record.key(), record.value());
        } catch (Exception e) {
            log.error("KAFKA | SEND FAILED | [reason={}]", e.getMessage(), e);
        }
    }
}
