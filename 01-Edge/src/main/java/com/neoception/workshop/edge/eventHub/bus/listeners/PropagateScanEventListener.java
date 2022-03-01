package com.neoception.workshop.edge.eventHub.bus.listeners;

import com.neoception.workshop.edge.eventHub.bus.events.PropagateScanEvent;
import com.neoception.workshop.edge.eventHub.outbound.ScansProducer;
import com.neoception.workshop.messages.Scan;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PropagateScanEventListener implements ApplicationListener<PropagateScanEvent> {
    private final ScansProducer scansProducer;

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void onApplicationEvent(@NonNull PropagateScanEvent message) {
        try {
            var eventsToProduce =
                    getEventsToProduce(message);

            eventsToProduce.forEach(scansProducer::produce);
        } catch (Exception e) {
            log.error("Unable to propagate Scan event!", e);
        }
    }

    private List<Scan> getEventsToProduce(PropagateScanEvent message) {
        List<Scan> eventsToProduce = new LinkedList<>();

        eventsToProduce.add(message.getEventData());

        return eventsToProduce;
    }
}
