package com.configuration.kafka.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class MessageSerializer implements Serializer<Message> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Message message) {
        try {
            if (message == null){
                log.warn("Null received at serializing");
                return null;
            }
            log.debug("Serializing...");
            return objectMapper.writeValueAsBytes(message);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }

    }

    @Override
    public byte[] serialize(String topic, Headers headers, Message message) {
        return Serializer.super.serialize(topic, headers, message);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
