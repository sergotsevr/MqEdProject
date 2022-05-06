package com.service;

import com.configuration.rabbirmq.RabbitConfiguration;
import com.model.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@ConditionalOnBean(RabbitConfiguration.class)
public class RabbitService implements QueueService{

    @Autowired
    AmqpTemplate template;

    @Override
    public Message readMessage() {
        return null;
    }
    @Override
    public void writeMessage(Message message) {
        template.convertAndSend(message.getMessageText().getBytes(StandardCharsets.UTF_8));
    }
}
