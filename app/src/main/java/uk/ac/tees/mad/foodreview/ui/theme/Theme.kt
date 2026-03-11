package uk.ac.tees.mad.foodreview.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
private val LightColorScheme = lightColorScheme(
    primary = Teal20,
    onPrimary = White,
    primaryContainer = Teal80,
    onPrimaryContainer = Teal10,

    secondary = Cyan40,
    onSecondary = Black,

    background = BackgroundLight,
    onBackground = OnLight,

    surface = SurfaceLight,
    onSurface = OnLight,

    error = ErrorLight,
    onError = White,

    outline = OutlineLight
)

private val DarkColorScheme = darkColorScheme(
    primary = Teal40,
    onPrimary = Teal10,
    primaryContainer = Teal10,
    onPrimaryContainer = Teal80,

    secondary = Cyan60,
    onSecondary = Cyan10,

    background = BackgroundDark,
    onBackground = OnDark,

    surface = SurfaceDark,
    onSurface = OnDark,

    error = ErrorDark,
    onError = Black,

    outline = OutlineDark
)

@Composable
fun FoodReviewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content ,
        shapes = Shapes
    )
}