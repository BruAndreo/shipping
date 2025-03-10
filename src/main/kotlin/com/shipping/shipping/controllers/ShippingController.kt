package com.shipping.shipping.controllers

import com.shipping.shipping.dto.ShippingResponseDTO
import com.shipping.shipping.services.ShippingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("shipping")
class ShippingController(
    val service: ShippingService
) {

    @GetMapping
    fun getShippingOptions(): List<ShippingResponseDTO> {
        // get shippings options from db
        // order
        // return


        return service.getShippingOptions()
    }

}
