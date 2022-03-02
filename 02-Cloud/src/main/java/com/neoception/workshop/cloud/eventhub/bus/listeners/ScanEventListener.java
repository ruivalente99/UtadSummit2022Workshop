package com.neoception.workshop.cloud.eventhub.bus.listeners;

import com.neoception.workshop.cloud.eventhub.bus.events.ScanEvent;
import com.neoception.workshop.cloud.box.BoxService;
import com.neoception.workshop.cloud.utils.DateTimeUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScanEventListener implements ApplicationListener<ScanEvent> {

    private final BoxService boxService;

    @Override
    public void onApplicationEvent(@NonNull ScanEvent message) {
        try {
            var eventData = message.getEventData();

            var boxId =
                    UUID.fromString(eventData.getBody().getBox().getBoxId());
            var timestamp =
                    DateTimeUtils.dateFromTimestamp(eventData.getHeader().getTimestamp());

            log.debug("SCAN ACTION | HANDLE | STARTED | [boxId={}]", boxId);
            var box = boxService.create(boxId, timestamp);
            log.debug("SCAN ACTION | HANDLE | FINISHED | [boxId={}]", boxId);

        } catch (Exception e) {
            log.error("Unable to process Scan event!", e);
        }
    }
}
