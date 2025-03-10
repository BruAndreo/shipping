package com.shipping.shipping.repositories

import com.shipping.shipping.domain.ShippingOptions
import com.shipping.shipping.domain.ShippingType
import org.springframework.stereotype.Repository

@Repository
class ShippingRepository {

    fun getOptions(): List<ShippingOptions> {
        return listOf(
            ShippingOptions("a", ShippingType.DELIVERY, 25.0, 3, 100.0),
            ShippingOptions("b", ShippingType.CUSTOM, 27.2, 3, 100.0),
            ShippingOptions("c", ShippingType.PICKUP, 15.0, 2, 100.0),
            ShippingOptions("d", ShippingType.CUSTOM, 27.2, 5, 150.0),
        )
    }

}