package com.shipping.shipping.controllers

import com.shipping.shipping.dto.ShippingResponseDTO
import com.shipping.shipping.services.ShippingService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("shipping")
class ShippingController(
    val service: ShippingService
) {

    @GetMapping
    fun getShippingOptions(@RequestParam("distance") distance: Double): List<ShippingResponseDTO> {
        if (distance < 1) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
        return service.getShippingOptions(distance)
    }

}
