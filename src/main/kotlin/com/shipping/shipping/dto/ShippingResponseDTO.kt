package com.shipping.shipping.dto

import com.shipping.shipping.domain.ShippingType

data class ShippingResponseDTO(
    val name: String,
    val type: ShippingType,
    val cost: Double,
    val estimatedDays: Int
)
