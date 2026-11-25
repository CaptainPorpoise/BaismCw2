package com.example.baismcw2

import java.math.BigDecimal
import java.math.RoundingMode

data class DiscountResult(val finalPrice: BigDecimal, val savings: BigDecimal)

/** Obliczenia dziesiętne bez błędów reprezentacji liczb zmiennoprzecinkowych. */
object DiscountCalculator {
    fun calculate(priceText: String, discountText: String): DiscountResult {
        val price = parse(priceText, "Podaj poprawną cenę.")
        val discount = parse(discountText, "Podaj poprawny rabat.")
        require(price >= BigDecimal.ZERO) { "Cena nie może być ujemna." }
        require(price <= BigDecimal("999999999.99")) { "Cena jest zbyt duża." }
        require(price.stripTrailingZeros().scale() <= 2) { "Cena może mieć najwyżej 2 miejsca po przecinku." }
        require(discount >= BigDecimal.ZERO && discount <= BigDecimal("100")) {
            "Rabat musi mieścić się w zakresie od 0 do 100%."
        }
        val savings = price.multiply(discount).movePointLeft(2).setScale(2, RoundingMode.HALF_UP)
        return DiscountResult(price.subtract(savings).setScale(2), savings)
    }

    private fun parse(text: String, message: String): BigDecimal {
        val normalized = text.trim().replace(',', '.')
        require(normalized.length <= 32 && normalized.matches(Regex("[+-]?[0-9]+(\\.[0-9]+)?"))) { message }
        return normalized.toBigDecimalOrNull() ?: throw IllegalArgumentException(message)
    }
}