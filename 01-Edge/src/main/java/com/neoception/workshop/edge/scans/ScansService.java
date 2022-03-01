package com.neoception.workshop.edge.scans;

import com.neoception.workshop.edge.eventHub.bus.EventBus;
import com.neoception.workshop.edge.eventHub.bus.events.PropagateScanEvent;
import com.neoception.workshop.edge.eventHub.helpers.ScansMessageHelpers;
import com.neoception.workshop.edge.utils.DateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScansService {

    private final DateTime dateTime;
    private final EventBus eventBus;

    public UUID generateRandomScan() {

        log.debug("RANDOM SCAN | PROCESSING | PROGRESS | Generating a random scan!");

        UUID boxId = UUID.randomUUID();

        publishRandomScan(boxId);

        log.info("RANDOM SCAN | PROCESSING | COMPLETED | [boxId={}]", boxId);

        return boxId;
    }

    private void publishRandomScan(UUID boxId) {

        var event =
                ScansMessageHelpers.setScanBoxMessage(boxId);

        var body = com.neoception.workshop.messages.Scan.Body
                .newBuilder()
                .setBox(event)
                .build();

        var message = ScansMessageHelpers
                .newMessage(
                        dateTime.utcNow(),
                        body);

        eventBus.publish(
                new PropagateScanEvent(
                        this,
                        message));
    }
}
