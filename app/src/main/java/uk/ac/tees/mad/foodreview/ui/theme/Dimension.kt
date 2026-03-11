package uk.ac.tees.mad.foodreview.ui.theme

import androidx.compose.ui.unit.dp

/**
 * Centralized dimensions for the app
 * Helps maintain consistency across UI
 */

object Dimens {

    // 🔹 GENERAL SPACING (Used everywhere)
    val ExtraSmall = 4.dp        // very tight spacing (icons, small gaps)
    val Small = 8.dp             // small padding between elements
    val Medium = 16.dp           // default padding (most used)
    val Large = 24.dp            // section spacing
    val ExtraLarge = 32.dp       // large gaps between major sections

    // 🔹 SCREEN PADDING
    val ScreenHorizontal = 16.dp // standard horizontal padding for screens
    val ScreenVertical = 16.dp   // standard vertical padding

    // 🔹 CARD & CONTAINER
    val CardPadding = 16.dp      // inner padding for cards
    val CardCornerRadius = 12.dp // rounded corners for cards

    // 🔹 TEXT FIELD
    val TextFieldPadding = 12.dp // inside text fields
    val TextFieldHeight = 56.dp  // standard height for input fields

    // 🔹 BUTTONS
    val ButtonHeight = 48.dp     // standard button height
    val ButtonCornerRadius = 10.dp
    val ButtonPaddingHorizontal = 16.dp
    val ButtonPaddingVertical = 12.dp

    // 🔹 ICONS
    val IconSmall = 16.dp        // small icons (inline, text)
    val IconMedium = 24.dp       // default icon size (most used)
    val IconLarge = 32.dp        // prominent icons

    // 🔹 IMAGE / AVATAR
    val ImageSmall = 40.dp
    val ImageMedium = 64.dp
    val ImageLarge = 100.dp

    // 🔹 TOP BAR
    val TopBarHeight = 56.dp     // standard Material top bar height
    val TopBarIconSize = 24.dp

    // 🔹 BOTTOM BAR
    val BottomBarHeight = 60.dp
    val BottomBarIconSize = 24.dp

    // 🔹 LIST ITEMS
    val ListItemPadding = 12.dp
    val ListItemSpacing = 8.dp

    // 🔹 DIVIDER
    val DividerThickness = 1.dp
    val DividerSpacing = 8.dp

    // 🔹 ELEVATION (for cards, buttons)
    val ElevationSmall = 2.dp
    val ElevationMedium = 4.dp
    val ElevationLarge = 8.dp

    // 🔹 RATING (Stars etc.)
    val RatingIconSize = 20.dp   // stars in rating bar

    // 🔹 SPLASH SCREEN
    val SplashLogoSize = 120.dp  // app logo size
    val LoadingIndicatorSize = 40.dp

    // 🔹 FAB (Floating Action Button)
    val FabSize = 56.dp
    val FabIconSize = 24.dp

    // 🔹 BORDER
    val BorderThin = 1.dp
    val BorderMedium = 2.dp

    // 🔹 REVIEW CARD SPECIFIC (your app)
    val ReviewCardSpacing = 12.dp
    val ReviewCardCorner = 12.dp
    val ReviewCardPadding = 16.dp

    // 🔹 SETTINGS SCREEN
    val SettingsItemHeight = 50.dp
    val SettingsIconSize = 22.dp
}