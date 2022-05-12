package com.kafka;

import com.configuration.kafka.KafkaConfiguration;
import com.model.Message;
import com.service.QueueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yml", properties = "mq.type = redis")
public class SpeedTest {
    @Autowired
    QueueService service;

    @Test
    public void speedTest() {
        Message message = prepareMessage();
        long startTime = System.currentTimeMillis();
        service.writeMessage(message);
        service.readMessage();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }

    private Message prepareMessage() {
        return new Message("Test message");
    }

    @Bean
    KafkaConfiguration kafkaConfiguration(){
        return new KafkaConfiguration();
    }
}
