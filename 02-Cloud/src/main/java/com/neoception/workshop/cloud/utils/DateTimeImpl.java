package com.neoception.workshop.cloud.utils;

import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class DateTimeImpl implements DateTime {
    @Override
    public ZonedDateTime utcNow() {
        return ZonedDateTime.now(ZoneOffset.UTC);
    }
}
