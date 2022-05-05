package com.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "mq", name = "type", havingValue = "kafka")
@Slf4j
public class KafkaService implements QueueService{
    private Message message = new Message("Init message");

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;


    @Override
    public Message readMessage() {
        return message;
    }

    @Override
    public void writeMessage(Message message) {
        kafkaTemplate.send("top", message);
    }

    @KafkaListener(topics = "top", groupId = "myGroup")
    public void listenGroupFoo(Message message) {
        log.info("Received Message " + message);
        this.message = message;
    }

}
