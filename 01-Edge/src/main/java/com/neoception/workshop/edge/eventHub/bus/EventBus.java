package com.neoception.workshop.edge.eventHub.bus;

import org.springframework.context.ApplicationEvent;

public interface EventBus {
    void publish(ApplicationEvent event);
}
