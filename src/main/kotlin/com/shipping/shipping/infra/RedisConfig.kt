package com.shipping.shipping.infra

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisNode
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
@EnableRedisRepositories
class RedisConfig (
    @Value("\${spring.data.redis.redis-mode-cluster}")
    private val redisInModeCluster: Boolean,
    @Value("\${spring.data.redis.cluster.cache-name.otp.tll}")
    private val redisCacheNameOtpTtlMinutes: Long
) {
    @Bean
    fun redisConnectionFactory() : RedisConnectionFactory {
        val redisHost = System.getenv("REDIS_HOST")
        val redisPort = System.getenv("REDIS_PORT")?.toInt()
        if (redisInModeCluster) {
            val clusterConfig = RedisClusterConfiguration()
                .apply {
                    val redisNode = RedisNode(redisHost, redisPort!!)
                    addClusterNode(redisNode)
                }
            return LettuceConnectionFactory(clusterConfig)
        }
        val standaloneConfig = RedisStandaloneConfiguration(redisHost, redisPort!!)
        return LettuceConnectionFactory(standaloneConfig)
    }

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<Any, Any> {
        val template = RedisTemplate<Any, Any>()
        template.connectionFactory = redisConnectionFactory

        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()

        return template
    }

    @Bean
    fun cacheManager(redisConnectionFactory: RedisConnectionFactory): RedisCacheManager {
        val config = RedisCacheConfiguration
            .defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(redisCacheNameOtpTtlMinutes))
        return RedisCacheManager
            .builder(redisConnectionFactory)
            .cacheDefaults(config)
            .build()
    }
}
