package com.configuration.redis;

import com.model.Message;
import com.service.redis.RedisReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import java.util.UUID;
import java.util.concurrent.Executors;

@Configuration
@ConditionalOnProperty(prefix = "mq", name = "type", havingValue = "redis")
public class RedisConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Message> redisTemplate() {
        final RedisTemplate<String, Message> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Message.class));
        return template;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic(UUID.randomUUID().toString());
    }

    @Bean
    @Autowired
    RedisMessageListenerContainer redisMessageListenerContainer(RedisReceiver redisReceiver) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(new MessageListenerAdapter(redisReceiver), topic());
        container.setTaskExecutor(Executors.newFixedThreadPool(4));
        return container;
    }
}
