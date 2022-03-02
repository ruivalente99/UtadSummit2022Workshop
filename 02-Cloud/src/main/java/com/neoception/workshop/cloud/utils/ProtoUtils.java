package com.neoception.workshop.cloud.utils;

import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

import java.util.UUID;

public class ProtoUtils {
    public static <T extends MessageOrBuilder> T toProto(String protoJsonStr, T message) {
        try {
            Message.Builder builder = message.getDefaultInstanceForType().toBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(protoJsonStr, builder);
            return (T) builder;
        } catch (Exception e) {
            throw new RuntimeException("Error converting Proto to json", e);
        }
    }

    public static <T extends MessageOrBuilder> String fromProtoToString(T message) {
        try {
            return JsonFormat.printer().omittingInsignificantWhitespace().print(message);
        } catch (Exception e) {
            throw new RuntimeException("Error converting Proto to string", e);
        }
    }

    public static UUID uuidFromString(String uuid) {
        return uuid == null || uuid.isEmpty() ? null : UUID.fromString(uuid);
    }
}
