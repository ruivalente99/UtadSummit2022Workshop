package com.neoception.workshop.cloud.eventhub.bus.events;

import com.neoception.workshop.messages.Scan;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class ScanEvent extends ApplicationEvent {
    @Getter
    private final Scan eventData;

    public ScanEvent(Object source, Scan eventData) {
        super(source);
        this.eventData = eventData;
    }
}
