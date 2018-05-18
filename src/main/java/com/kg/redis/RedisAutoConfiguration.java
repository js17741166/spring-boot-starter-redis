package com.kg.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: RedisAutoConfiguration
 * @Description: Redis自动配置类
 * @author KG
 * @date 2017年4月26日 下午4:48:31
 *
 */
@Configuration
@EnableConfigurationProperties({RedisPoolProperties.class})
@ConditionalOnClass(value = { JedisPoolConfig.class, JedisPool.class, Jedis.class  })
public class RedisAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RedisAutoConfiguration.class);


    @Autowired
    private RedisPoolProperties redisPoolProperties;


    @Bean
    public JedisPool jedisPool(){
        logger.info("RedisAutoConfiguration.jedisPool ---> " + redisPoolProperties.getJedisPool());
        return redisPoolProperties.getJedisPool();
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        logger.info("RedisAutoConfiguration.jedisPoolConfig ---> " + redisPoolProperties.getJedisPoolConfig());

        return redisPoolProperties.getJedisPoolConfig();
    }




    
}

