package com.neoception.workshop.cloud.eventhub.inbound;

import com.neoception.workshop.cloud.utils.ProtoUtils;
import com.neoception.workshop.messages.Scan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class ScansListener {

    private final ScansMessageHandler scansMessageHandler;

    @KafkaListener(
            topics = "${KAFKA_SCANS_TOPIC}",
            groupId = "${KAFKA_GROUP_ID}"
    )

    public void listen(ConsumerRecord<String, String> record) {
        log.debug("SCAN | RECEIVED | [record={}]", record);

        try {
            Scan message = getMessage(record.value());
            scansMessageHandler.processMessage(message);
        } catch (Exception e) {
            log.error("SCAN | RECEIVE FAILED | [reason={}]", e.getMessage());
        }
    }

    private Scan getMessage(String payload) {
        return ProtoUtils.toProto(payload, Scan.newBuilder()).build();
    }
}