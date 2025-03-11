package com.shipping.shipping.services

import com.shipping.shipping.dto.ShippingResponseDTO
import com.shipping.shipping.repositories.ShippingRepository
import org.springframework.stereotype.Service

@Service
class ShippingService(
    private val repository: ShippingRepository
) {

    fun getShippingOptions(): List<ShippingResponseDTO> {
        val options = repository.getOptions()

        val orderedOptions = options.sortedBy { it.cost }.sortedBy { it.estimatedDays }

        return orderedOptions.map { it.toDto() }
    }
}
