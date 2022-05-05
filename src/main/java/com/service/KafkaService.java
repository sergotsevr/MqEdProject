package com.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "mq", name = "type", havingValue = "kafka")
public class KafkaService implements QueueService{

    ObjectMapper objectMapper = new ObjectMapper();
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
        System.out.println("Received Message in group foo: " + message);
        this.message = message;
    }


}
