package com;

import com.model.Message;
import com.service.QueueService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSpeedTest {

    //идея ругается зря, т.к. бин создается только один в зависимости от значения проперти mq.type
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
}
