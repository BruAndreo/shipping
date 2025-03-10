package com.shipping.shipping

import com.shipping.shipping.domain.ShippingOptions
import com.shipping.shipping.domain.ShippingType
import java.util.*

object ShippingOptionMock {
    fun create(
        name: String = UUID.randomUUID().toString(),
        type: ShippingType = ShippingType.PICKUP,
        cost: Double = 10.0,
        estimatedDays: Int = 5,
        maxDistanceInMeters: Double = 100.0
    ) = ShippingOptions(
        name,
        type,
        cost,
        estimatedDays,
        maxDistanceInMeters
    )
}