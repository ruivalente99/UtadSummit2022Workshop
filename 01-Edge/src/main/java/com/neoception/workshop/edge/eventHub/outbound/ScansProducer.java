package com.neoception.workshop.edge.eventHub.outbound;

import com.neoception.workshop.messages.Scan;
import com.neoception.workshop.edge.utils.DateTime;
import com.neoception.workshop.edge.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScansProducer extends BaseProducer {

    @Value("${KAFKA_SCANS_TOPIC}")
    private String segmentsTopic;

    public ScansProducer(KafkaTemplate<String, String> kafkaTemplate) {
        super(kafkaTemplate);
    }

    public boolean produce(Scan message) {
        try {
            ProducerRecord<String, String> record =
                    MessageUtils.newProducerRecord(segmentsTopic, message.getHeader(), message);
            send(record);
            return true;
        } catch (Exception e) {
            log.error("KAFKA SCANS | SEND FAILED | [reason={}]", e.getMessage(), e);
            return false;
        }
    }
}
