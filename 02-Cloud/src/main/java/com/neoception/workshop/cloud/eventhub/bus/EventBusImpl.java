package com.neoception.workshop.cloud.eventhub.bus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventBusImpl implements EventBus {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(ApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
