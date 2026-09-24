package com.ejemplo.alarmafamiliar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = White,
    primaryContainer = CardBlueTint,
    onPrimaryContainer = Navy,
    secondary = TealAccent,
    onSecondary = OnTealAccent,
    tertiary = Navy,
    onTertiary = White,
    background = AppBackground,
    onBackground = Navy,
    surface = White,
    onSurface = Navy,
    surfaceVariant = CardBlueTint,
    onSurfaceVariant = TextLight,
    outline = OutlineSoft,
    error = SosRed,
    onError = White
)

@Composable
fun AlarmaFamiliarTheme(
    @Suppress("UNUSED_PARAMETER") darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}