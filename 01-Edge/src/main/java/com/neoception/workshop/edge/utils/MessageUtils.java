package com.neoception.workshop.edge.utils;

import com.google.protobuf.MessageOrBuilder;
import com.neoception.workshop.messages.Header;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.ZonedDateTime;
import java.util.UUID;

public class MessageUtils {
    public static Header newHeader(ZonedDateTime dateTime) {
        var id = UUID.randomUUID().toString();

        return Header.newBuilder()
                .setTimestamp(DateTimeUtils.timestampFromDate(dateTime))
                .setId(id)
                .build();
    }

    public static ProducerRecord<String, String> newProducerRecord(String topic, Header header, MessageOrBuilder message) {

        ProducerRecord<String, String> record = new ProducerRecord<>(
                topic,
                ProtoUtils.fromProtoToString(message)
        );

        record.headers().add("id", header.getId().getBytes());
        record.headers().add("timestamp", header.getTimestamp().toByteArray());

        return record;
    }
}
