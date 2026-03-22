package uk.ac.tees.mad.foodreview.domain.model

import android.media.Rating

data class Review(
    val id: String ="",
    val foodName : String = "",
    val rating: Int = 0,
    val reviewText : String = "",
    val userId : String = "",
    val createdAt : Long = System.currentTimeMillis()
)
