package com.shipping.shipping.domain

import com.shipping.shipping.dto.ShippingResponseDTO

data class ShippingOptions(
    val name: String,
    val type: ShippingType,
    val cost: Double,
    val estimatedDays: Int,
    val maxDistanceInMeters: Double
) {
    fun toDto(): ShippingResponseDTO {
        return ShippingResponseDTO(name, type, cost, estimatedDays)
    }
}
