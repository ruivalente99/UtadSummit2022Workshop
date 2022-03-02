package com.neoception.workshop.cloud.eventhub.inbound;

import com.neoception.workshop.messages.Scan;
import com.neoception.workshop.cloud.eventhub.bus.EventBus;
import com.neoception.workshop.cloud.eventhub.bus.events.ScanEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScansMessageHandler {

    private final EventBus eventBus;

    public boolean processMessage(Scan message) {
        var processed = true;

        var eventType = message.getBody().getBodyCase();

        try {
            eventBus.publish(new ScanEvent(this, message));
        }
        catch(Exception ex) {
            processed = false;
        }

        return processed;
    }
}