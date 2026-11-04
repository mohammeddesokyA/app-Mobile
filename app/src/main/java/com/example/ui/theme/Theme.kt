package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = StarYellow,
    onPrimary = DarkNavy,
    primaryContainer = StarYellowLight,
    onPrimaryContainer = DarkNavy,
    secondary = CoralPink,
    onSecondary = SurfaceWhite,
    secondaryContainer = CoralPinkLight,
    onSecondaryContainer = CoralPinkDark,
    tertiary = SoftPurple,
    onTertiary = SurfaceWhite,
    tertiaryContainer = SoftPurpleLight,
    onTertiaryContainer = SoftPurple,
    background = BackgroundOffWhite,
    onBackground = DarkNavy,
    surface = SurfaceWhite,
    onSurface = DarkNavy,
    surfaceVariant = BackgroundOffWhite,
    onSurfaceVariant = TextGray,
    outline = CardBorder
)

private val DarkColorScheme = lightColorScheme(
    primary = StarYellow,
    onPrimary = DarkNavy,
    primaryContainer = StarYellowLight,
    onPrimaryContainer = DarkNavy,
    secondary = CoralPink,
    onSecondary = SurfaceWhite,
    background = BackgroundOffWhite,
    onBackground = DarkNavy,
    surface = SurfaceWhite,
    onSurface = DarkNavy
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = SurfaceWhite.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
