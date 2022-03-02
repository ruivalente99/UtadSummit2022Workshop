package com.neoception.workshop.cloud.eventhub.bus;

import org.springframework.context.ApplicationEvent;

public interface EventBus {
    void publish(ApplicationEvent event);
}
