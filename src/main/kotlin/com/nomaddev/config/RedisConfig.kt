package com.nomaddev.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
@PropertySource("classpath:application.properties")
open class RedisConfig() {

    @Value("\${redis.endpoint}") lateinit var redisHost: String
    @Value("\${redis.port}") lateinit var redisPort: String
    @Value("\${redis.password}") lateinit var redisPass: String

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val jedisConFactory = JedisConnectionFactory()
        jedisConFactory.hostName = redisHost
        jedisConFactory.port = redisPort.toInt()
        jedisConFactory.password = redisPass
        return jedisConFactory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        template.connectionFactory = jedisConnectionFactory()
        return template
    }
}