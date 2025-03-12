package com.shipping.shipping

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class ShippingApplication

fun main(args: Array<String>) {
	runApplication<ShippingApplication>(*args)
}
