package com.example.baismcw2

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun standardDiscount() {
        val result = DiscountCalculator.calculate("200", "15")

        assertEquals("171.00", result.finalPrice.toPlainString())
    }

    @Test
    fun zeroDiscount() {
        val result = DiscountCalculator.calculate("200", "0")

        assertEquals("200.00", result.finalPrice.toPlainString())
    }

    @Test
    fun invalidDiscountIsRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            DiscountCalculator.calculate("200", "110")
        }
    }
}