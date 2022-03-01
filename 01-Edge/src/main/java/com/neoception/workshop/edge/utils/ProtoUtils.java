package com.neoception.workshop.edge.utils;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

public class ProtoUtils {
    public static <T extends MessageOrBuilder> String fromProtoToString(T message) {
        try {
            return JsonFormat.printer().omittingInsignificantWhitespace().print(message);
        } catch (Exception e) {
            throw new RuntimeException("Error converting Proto to string", e);
        }
    }
}
