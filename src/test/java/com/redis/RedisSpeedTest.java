package com.redis;


import com.AbstractSpeedTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.yml", properties = "mq.type = redis")
@Slf4j
@Configuration
public class RedisSpeedTest extends AbstractSpeedTest {

    static Integer port = 6379;
    @ClassRule
    static final GenericContainer<?> redis = new GenericContainer("redis:3")
            .withExposedPorts(port);

    @DynamicPropertySource
    public static void configureRedis(DynamicPropertyRegistry registry) {

        redis.start();
        log.info("* 'Redis': spring.redis.host = {} ; spring.redis.port = {}",
                redis.getHost(), redis.getMappedPort(port));
        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
    }
}