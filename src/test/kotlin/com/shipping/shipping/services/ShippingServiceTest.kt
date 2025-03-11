package com.shipping.shipping.services

import com.shipping.shipping.ShippingOptionMock
import com.shipping.shipping.domain.ShippingOptions
import com.shipping.shipping.repositories.ShippingRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
class ShippingServiceTest {

    @MockK
    private lateinit var repository: ShippingRepository

    @InjectMockKs
    private lateinit var service: ShippingService

    private val defaulDistance = 50.0

    @Test
    fun `Should return ordened by different cost and same estimated days`() {
        val option1 = ShippingOptionMock.create(cost = 25.0)
        val option2 = ShippingOptionMock.create(cost = 100.0)
        val option3 = ShippingOptionMock.create(cost = 7.59)

        val options = listOf(option1, option2, option3)

        every { repository.getOptions() } returns options

        val result = service.getShippingOptions(defaulDistance)

        assertEquals(result[0], option3.toDto())
        assertEquals(result[1], option1.toDto())
        assertEquals(result[2], option2.toDto())
    }

    @Test
    fun `Should return ordened by same cost and different estimated days`() {
        val option1 = ShippingOptionMock.create(estimatedDays = 5)
        val option2 = ShippingOptionMock.create(estimatedDays = 6)
        val option3 = ShippingOptionMock.create(estimatedDays = 1)

        val options = listOf(option1, option2, option3)

        every { repository.getOptions() } returns options

        val result = service.getShippingOptions(defaulDistance)

        assertEquals(result[0], option3.toDto())
        assertEquals(result[1], option1.toDto())
        assertEquals(result[2], option2.toDto())
    }

    @Test
    fun `Should return ordened by different cost and different estimated days`() {
        val option1 = ShippingOptionMock.create(cost = 25.0, estimatedDays = 3)
        val option2 = ShippingOptionMock.create(cost = 50.0, estimatedDays = 1)
        val option3 = ShippingOptionMock.create(cost = 10.0, estimatedDays = 7)
        val option4 = ShippingOptionMock.create(cost = 10.0, estimatedDays = 10)

        val options = listOf(option1, option2, option3, option4)

        every { repository.getOptions() } returns options

        val result = service.getShippingOptions(defaulDistance)

        assertEquals(result[0], option3.toDto())
        assertEquals(result[1], option4.toDto())
        assertEquals(result[2], option1.toDto())
        assertEquals(result[3], option2.toDto())
    }

    @Test
    fun `Should return an empty list when not has any shipping option`() {
        val options = emptyList<ShippingOptions>()

        every { repository.getOptions() } returns options

        val result = service.getShippingOptions(defaulDistance)

        assertTrue { result.isEmpty() }
    }

    @Test
    fun `Should return ordened a list when has same cost and estimated dates`() {
        val option1 = ShippingOptionMock.create()
        val option2 = ShippingOptionMock.create()
        val option3 = ShippingOptionMock.create()

        val options = listOf(option1, option2, option3)

        every { repository.getOptions() } returns options

        val result = service.getShippingOptions(defaulDistance)

        assertEquals(result[0], option1.toDto())
        assertEquals(result[1], option2.toDto())
        assertEquals(result[2], option3.toDto())
    }

    @Test
    fun `Should return ordened a list with options inside the distance`() {
        val option1 = ShippingOptionMock.create(cost = 10.0, estimatedDays = 1, maxDistanceInMeters = 25.0)
        val option2 = ShippingOptionMock.create(cost = 20.0, estimatedDays = 1, maxDistanceInMeters = 100.0)
        val option3 = ShippingOptionMock.create(cost = 15.0, estimatedDays = 5, maxDistanceInMeters = 100.0)
        val option4 = ShippingOptionMock.create(cost = 20.0, estimatedDays = 7, maxDistanceInMeters = 500.0)

        val options = listOf(option1, option2, option3, option4)

        every { repository.getOptions() } returns options

        val result = service.getShippingOptions(defaulDistance)

        println(result)

        assertTrue(result.size == 3)
        assertEquals(result[0], option3.toDto())
        assertEquals(result[1], option2.toDto())
        assertEquals(result[2], option4.toDto())
        assertTrue { result.none { it == option1.toDto() } }
    }
}