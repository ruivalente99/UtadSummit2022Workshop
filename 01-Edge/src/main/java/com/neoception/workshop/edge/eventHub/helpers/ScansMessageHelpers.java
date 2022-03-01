package com.neoception.workshop.edge.eventHub.helpers;

import com.neoception.workshop.messages.Scan;
import com.neoception.workshop.edge.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.UUID;

@Slf4j
@Component
public class ScansMessageHelpers {

    public static Scan newMessage(ZonedDateTime utcNow, Scan.Body body) {
        return Scan
                .newBuilder()
                .setHeader(MessageUtils.newHeader(utcNow))
                .setBody(body)
                .build();
    }

    public static Scan.Box setScanBoxMessage(UUID boxId) {
        return Scan.Box.newBuilder()
                .setBoxId(boxId.toString())
                .build();
    }
}