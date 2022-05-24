package com.kafka;

import com.AbstractSpeedTest;
import com.configuration.kafka.KafkaConfiguration;
import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(KafkaConfiguration.class)
@TestPropertySource(
        locations = "classpath:application.yml", properties = "mq.type = kafka")
public class KafkaSpeedTest extends AbstractSpeedTest {

    @ClassRule
    public static KafkaContainer kafka;

    static {
        kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));
        kafka.start();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("kafka.bootstrap-servers",kafka::getBootstrapServers);
    }
}
