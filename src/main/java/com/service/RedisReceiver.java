package com.service;

import com.model.Message;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class RedisReceiver implements MessageListener {

    @Getter
    private Message lastMessage = new Message("init value");

    @Override
    @SneakyThrows
    public void onMessage(org.springframework.data.redis.connection.Message message, byte[] pattern) {
        String messageStr = new String(message.getBody(), StandardCharsets.UTF_8);
        lastMessage = new Message(messageStr);
        log.info("received message" + messageStr);
    }
}
