package com.neoception.workshop.edge.eventHub.bus.events;

import com.neoception.workshop.messages.Scan;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class PropagateScanEvent extends ApplicationEvent {
    @Getter
    private final Scan eventData;

    public PropagateScanEvent(Object source, Scan eventData) {
        super(source);
        this.eventData = eventData;
    }
}
