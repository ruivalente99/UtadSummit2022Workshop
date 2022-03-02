package com.neoception.workshop.cloud.utils;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTimeUtils {
    public static Instant instantFromTimestamp(Timestamp timestamp) {
        return Instant
                .ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
    }

    public static ZonedDateTime dateFromTimestamp(Timestamp timestamp) {
        return DateTimeUtils.instantFromTimestamp(timestamp).atZone(ZoneOffset.UTC);
    }

    public static Timestamp timestampFromDate(ZonedDateTime date) {
        if (date == null) {
            return null;
        }
        var instant = Instant.from(date);
        return Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }
}
