package com.shipping.shipping.repositories

import com.shipping.shipping.domain.ShippingOptions
import com.shipping.shipping.domain.ShippingType
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class ShippingRepository(private val redisTemplate: RedisTemplate<String, List<ShippingOptions>>) : RedisTemplateRepository {
    fun getOptions(): List<ShippingOptions> {
        return listOf(
            ShippingOptions("a", ShippingType.DELIVERY, 25.0, 3, 100.0),
            ShippingOptions("b", ShippingType.CUSTOM, 27.2, 3, 200.0),
            ShippingOptions("c", ShippingType.PICKUP, 15.0, 2, 10.0),
            ShippingOptions("d", ShippingType.CUSTOM, 27.2, 5, 50.0),
        )
    }

    override fun saveValue(key: String, value: List<ShippingOptions>) {
        redisTemplate.opsForValue().set(key, value)
    }

    override fun getValue(key: String): List<ShippingOptions> {
        TODO("Not yet implemented")
    }
}