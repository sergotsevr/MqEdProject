package com;

import com.model.Message;
import com.service.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractSpeedTest {

    //идея ругается зря, т.к. бин создается только один в зависимости от значения проперти mq.type
    @Autowired
    private QueueService service;

    private static final int timesToRun = 5;

    private static long startTime = 0;


    @RepeatedTest(timesToRun)
    public void speedTest() {
        Message message = prepareMessage();
        service.writeMessage(message);
        Message messageFromMq = service.readMessage();
        Assertions.assertNotNull(messageFromMq);
    }
    @BeforeAll
    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    @AfterAll
    public static void writeStatistic() {
        long endTime = System.currentTimeMillis();
        log.info("Total execution time: " + (endTime - startTime) + "ms");
    }

    private Message prepareMessage() {
        return new Message("Test message");
    }
}
