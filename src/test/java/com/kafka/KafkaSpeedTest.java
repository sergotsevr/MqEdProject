package com.kafka;

import com.AbstractSpeedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//раскоментировать для тестирования без поднятия кафки
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestPropertySource(
        locations = "classpath:application.yml", properties = "mq.type = kafka")
public class KafkaSpeedTest extends AbstractSpeedTest {

}
