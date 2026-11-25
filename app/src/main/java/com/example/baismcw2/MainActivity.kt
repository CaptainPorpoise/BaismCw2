package com.example.baismcw2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.baismcw2.ui.theme.BaismCw2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaismCw2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DiscountScreen(Modifier.padding(padding))
                }
            }
        }
    }
}

@Composable
private fun DiscountScreen(modifier: Modifier = Modifier) {
    var price by rememberSaveable { mutableStateOf("") }
    var discount by rememberSaveable { mutableStateOf("") }
    var submitted by rememberSaveable { mutableStateOf(false) }
    val calculation = if (submitted) runCatching { DiscountCalculator.calculate(price, discount) } else null

    Column(
        modifier.fillMaxSize().verticalScroll(rememberScrollState()).imePadding().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Kalkulator rabatu", style = MaterialTheme.typography.headlineLarge)
        Text("Sprawdź cenę po obniżce i kwotę oszczędności.")
        OutlinedTextField(
            value = price,
            onValueChange = { price = it; submitted = false },
            label = { Text("Cena przed rabatem (zł)") },
            supportingText = { Text("Np. 199,99 — maksymalnie 2 miejsca po przecinku") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = discount,
            onValueChange = { discount = it; submitted = false },
            label = { Text("Rabat (%)") },
            supportingText = { Text("Od 0 do 100") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { submitted = true }, modifier = Modifier.fillMaxWidth()) { Text("Oblicz") }
        calculation?.onSuccess { result ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Cena po rabacie", style = MaterialTheme.typography.titleMedium)
                    Text("${result.finalPrice.toPlainString().replace('.', ',')} zł", style = MaterialTheme.typography.headlineMedium)
                    Text("Oszczędzasz: ${result.savings.toPlainString().replace('.', ',')} zł")
                }
            }
        }?.onFailure { error ->
            Text(error.message ?: "Sprawdź wprowadzone dane.", color = MaterialTheme.colorScheme.error)
        }
        TextButton(onClick = { price = ""; discount = ""; submitted = false }) { Text("Wyczyść") }
        Text("Działa offline. Nie zapisuje danych na dysku ani nie wysyła ich do sieci.", style = MaterialTheme.typography.bodySmall)
    }
}