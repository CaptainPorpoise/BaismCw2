# Kalkulator rabatu

Aplikacja Android napisana w Kotlinie z interfejsem Jetpack Compose. Oblicza cenę po rabacie i oszczędność.

## Funkcje

- Cena w PLN, rabat od 0 do 100%.
- Obsługa przecinka i kropki jako separatora dziesiętnego.
- Komunikaty dla pustych pól, błędnych danych i wartości poza zakresem.
- Czyszczenie formularza i odtwarzanie pól po obrocie ekranu.
- Działanie offline, bez zapisu danych na dysku i uprawnień sieciowych.

## Obliczenia

Logika znajduje się w DiscountCalculator.kt, niezależnie od interfejsu w MainActivity.kt. Cena ma najwyżej dwa miejsca dziesiętne i mieści się w zakresie 0–999 999 999,99 zł. BigDecimal zapewnia obliczenia dziesiętne. Oszczędność jest zaokrąglana do groszy metodą HALF_UP i odejmowana od ceny.

Przykład: 200 zł i rabat 15% daje 170,00 zł oraz 30,00 zł oszczędności.

## Uruchomienie

Otwórz projekt w Android Studio, poczekaj na synchronizację Gradle i zainstaluj wymagane składniki SDK (compileSdk 36.1). Wybierz emulator lub urządzenie z API 24+ i uruchom moduł app. Użyj JDK 17 lub zgodnego JDK dołączonego do Android Studio.

Budowanie APK w PowerShell, w katalogu projektu:

```powershell
.\gradlew.bat :app:assembleDebug
```

Na Linuxie:

```bash
bash ./gradlew :app:assembleDebug
```

Plik wynikowy: app/build/outputs/apk/debug/app-debug.apk. Jest to APK do testów, nie wydanie produkcyjne.

## Struktura

- MainActivity.kt — formularz i prezentacja wyników.
- DiscountCalculator.kt — walidacja oraz obliczenia.
- ui/theme — motyw aplikacji.
- gradle/libs.versions.toml — wersje bibliotek i narzędzi.

Projekt zawiera przykłady testów wygenerowane przez Android Studio. Testy kalkulatora oraz konfiguracja CI/CD są kolejnymi etapami ćwiczenia.