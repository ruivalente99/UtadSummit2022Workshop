package com.neoception.workshop.edge.utils;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.ZonedDateTime;

public class DateTimeUtils {

    public static Timestamp timestampFromDate(ZonedDateTime date) {
        if (date == null) {
            return null;
        }
        var instant = Instant.from(date);
        return Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }
}
