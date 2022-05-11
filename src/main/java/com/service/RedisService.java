package com.service;

import com.configuration.rabbirmq.RabbitConfiguration;
import com.configuration.redis.RedisConfiguration;
import com.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ConditionalOnBean(RedisConfiguration.class)
public class RedisService implements QueueService{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ChannelTopic topic;

    @Override
    public Message readMessage() {
        return null;
    }

    @Override
    public void writeMessage(Message message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
        log.info("Sending to Redis message: " + message + "into the topic: " + topic.getTopic());
    }



    public RedisService(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }
    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
