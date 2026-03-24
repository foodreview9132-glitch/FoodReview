package uk.ac.tees.mad.foodreview.ui.reviews

import uk.ac.tees.mad.foodreview.domain.model.Review

data class ReviewListUiState(
    val list :List<Review> = emptyList() ,
    val isLoading : Boolean = false ,
    val error: String ? = null
)