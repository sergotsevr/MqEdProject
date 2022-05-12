package com.rabbit;

import com.AbstractSpeedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yml", properties = "mq.type = rabbitmq")
public class RabbitSpeedTest extends AbstractSpeedTest {

}
