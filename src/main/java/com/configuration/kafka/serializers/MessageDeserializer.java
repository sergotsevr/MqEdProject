package com.configuration.kafka.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class MessageDeserializer implements Deserializer<Message> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Message deserialize(String s, byte[] data) {
        try {
            if (data == null){
                log.warn("Null received at deserializing");
                return null;
            }
            log.debug("Deserializing...");
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), Message.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Message" + e);
        }
    }

    @Override
    public Message deserialize(String topic, Headers headers, byte[] data) {
        return deserialize(topic, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
