package com.service;

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
public class RedisService implements QueueService {
    @Autowired
    private RedisTemplate<String, Message> redisTemplate;
    @Autowired
    private ChannelTopic topic;

    @Autowired
    RedisReceiver redisReceiver;

    @Override
    public Message readMessage() {
        return redisReceiver.getLastMessage();
    }

    @Override
    public void writeMessage(Message message) {
        log.info("Sending to Redis message: " + message.getMessageText() + "into the topic: " + topic.getTopic());
        redisTemplate.convertAndSend(topic.getTopic(), message.getMessageText());
    }



    public RedisService(RedisTemplate<String, Message> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }
}
