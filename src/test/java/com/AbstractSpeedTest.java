package com;

import com.model.Message;
import com.service.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractSpeedTest {

    //идея ругается зря, т.к. бин создается только один в зависимости от значения проперти mq.type
    @Autowired
    private QueueService service;
    private static long deltaTime = 0;

    private static final int timesToRun = 5;

    @RepeatedTest(timesToRun)
    public void speedTest() {
        Message message = prepareMessage();
        long startTime = System.currentTimeMillis();
        service.writeMessage(message);
        service.readMessage();
        long endTime = System.currentTimeMillis();
        deltaTime += endTime - startTime;
        log.info("One iteration execution time: " + deltaTime + "ms");
    }

    @AfterAll
    public static void writeStatistic() {
        log.info("Average execution time: " + (deltaTime / timesToRun) + "ms");
    }

    private Message prepareMessage() {
        return new Message("Test message");
    }
}
