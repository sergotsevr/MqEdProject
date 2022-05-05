package com.service;


import com.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "mq", name = "type", havingValue = "kafka")
public class KafkaService implements QueueService{

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Override
    public Message readMessage() {
        return null;
    }

    @Override
    public void writeMessage(Message message) {
        kafkaTemplate.send("top", message);
    }
}
