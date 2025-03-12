package com.shipping.shipping.repositories

import com.shipping.shipping.domain.ShippingOptions

interface RedisTemplateRepository {
    fun saveValue(key: String, value: ShippingOptions)
    fun getValue(key: String): ShippingOptions?
}
