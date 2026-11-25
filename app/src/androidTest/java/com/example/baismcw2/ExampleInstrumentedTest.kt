package com.example.baismcw2

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun calculateDiscountDisplaysCorrectResult() {
        composeRule
            .onNodeWithText("Cena przed rabatem (zł)")
            .performTextInput("200")

        composeRule
            .onNodeWithText("Rabat (%)")
            .performTextInput("15")

        composeRule
            .onNodeWithText("Oblicz")
            .performClick()

        composeRule
            .onNodeWithText("170,00 zł")
            .assertIsDisplayed()
    }
}