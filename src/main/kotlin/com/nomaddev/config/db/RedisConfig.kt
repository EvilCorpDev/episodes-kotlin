package com.nomaddev.config.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
@PropertySource("classpath:application.properties")
open class RedisConfig @Autowired constructor(val env: Environment) {

    val redisHost = "redis.endpoint"
    val redisPort = "redis.port"
    val redisPass = "redis.password"

    @Bean
    open fun jedisConnectionFactory(): JedisConnectionFactory {
        val jedisConFactory = JedisConnectionFactory()
        jedisConFactory.hostName = env.getProperty(redisHost)
        jedisConFactory.port = env.getProperty(redisPort).toInt()
        jedisConFactory.password = env.getProperty(redisPass)
        return jedisConFactory
    }

    @Bean
    open fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = jedisConnectionFactory()
        return template
    }
}