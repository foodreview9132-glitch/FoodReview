package uk.ac.tees.mad.foodreview.ui.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Shapes used across the app
 * Defines corner radius for UI components
 */

val Shapes = Shapes(

    // 🔹 Small components (chips, small buttons)
    small = RoundedCornerShape(6.dp),

    // 🔹 Medium components (cards, text fields)
    medium = RoundedCornerShape(12.dp),

    // 🔹 Large components (dialogs, sheets, big containers)
    large = RoundedCornerShape(20.dp)
)