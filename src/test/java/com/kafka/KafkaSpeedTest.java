package com.kafka;

import com.AbstractSpeedTest;
import com.service.QueueService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yml", properties = "mq.type = kafka")
public class KafkaSpeedTest extends AbstractSpeedTest {

    //идея ругается зря, т.к. бин создается только один в зависимости от значения проперти mq.type
    @Autowired
    QueueService service;

}
