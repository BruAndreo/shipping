package com.shipping.shipping.services

import com.shipping.shipping.domain.ShippingOptions
import com.shipping.shipping.dto.ShippingResponseDTO
import com.shipping.shipping.repositories.ShippingRepository
import org.springframework.stereotype.Service

@Service
class ShippingService(
    private val repository: ShippingRepository
) {

    fun getShippingOptions(distance: Double): List<ShippingResponseDTO> {
        val options = repository.getOptions()
        return options
            .filter { it.maxDistanceInMeters >= distance }
            .sortedWith(compareBy(ShippingOptions::cost, ShippingOptions::estimatedDays))
            .map { it.toDto() }
    }

    fun createShippingOption(shippingOptions: ShippingOptions) {
        repository.saveValue(shippingOptions.name, shippingOptions)
    }
}
