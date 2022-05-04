package com.service;

import com.model.Message;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "mq", name = "type", havingValue = "mock")
public class MockReadService implements QueueService {

    private Message message = new Message("Hello world");

    @Override
    public Message readMessage() {
        return message;
    }

    @Override
    public void writeMessage(Message message) {
        this.message = message;
    }
}
