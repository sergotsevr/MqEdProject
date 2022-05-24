package com.rabbit;

import com.AbstractSpeedTest;
import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yml", properties = "mq.type = rabbitmq")
public class RabbitSpeedTest extends AbstractSpeedTest {

    @ClassRule
    public static RabbitMQContainer rabbitMQContainer;

    static {
        rabbitMQContainer = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.8.19-alpine"));
        rabbitMQContainer.start();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("rabbit.port",rabbitMQContainer::getAmqpPort);
    }

}
