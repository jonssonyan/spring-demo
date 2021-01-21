package com.springboot.config;

import com.springboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConditionalOnClass(RedisService.class)
public class RedisConfig {
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    @ConditionalOnMissingBean
    public RedisService redisService() {
        return new RedisService(redisTemplate);
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // redis configuration
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * key 的序列化器
     */
    private final StringRedisSerializer keyRedisSerializer = new StringRedisSerializer();

    /**
     * value 的序列化器
     */
    private final RedisFastJsonSerializer<Object> valueRedisSerializer = new RedisFastJsonSerializer<>(Object.class);

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        // RedisCacheConfiguration - 值的序列化方式
        RedisSerializationContext.SerializationPair<Object> serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(valueRedisSerializer);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(serializationPair);

        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // 配置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 值序列化-RedisFastJsonSerializer
        redisTemplate.setValueSerializer(valueRedisSerializer);
        redisTemplate.setHashValueSerializer(valueRedisSerializer);
        // 键序列化-StringRedisSerializer
        redisTemplate.setKeySerializer(keyRedisSerializer);
        redisTemplate.setHashKeySerializer(keyRedisSerializer);

        return redisTemplate;
    }
}
