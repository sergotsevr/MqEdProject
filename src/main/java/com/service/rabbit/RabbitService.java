package com.service.rabbit;

import com.configuration.rabbirmq.RabbitConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Message;
import com.service.QueueService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@ConditionalOnBean(RabbitConfiguration.class)
public class RabbitService implements QueueService {

    ObjectMapper objectMapper = new ObjectMapper();
    Message message = new Message("Init rabbitmq message");

    @Autowired
    AmqpTemplate template;

    @Override
    public Message readMessage() {
        return message;
    }
    @Override
    @SneakyThrows
    public void writeMessage(Message message) {
        template.convertAndSend("queue1", objectMapper.writeValueAsString(message));
    }

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) throws IOException {
        log.info("Received from queue: " + message);
        this.message = objectMapper.readValue(message, Message.class);
    }
}
