package com.shipping.shipping.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.shipping.shipping.domain.ShippingType

data class ShippingResponseDTO(
    val name: String,
    val type: ShippingType,
    val cost: Double,
    @JsonProperty("estimated_days")
    val estimatedDays: Int
)
