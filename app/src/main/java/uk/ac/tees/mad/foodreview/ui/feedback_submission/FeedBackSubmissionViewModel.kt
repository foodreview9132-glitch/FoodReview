package uk.ac.tees.mad.foodreview.ui.feedback_submission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.foodreview.domain.model.Review
import uk.ac.tees.mad.foodreview.domain.repository.FoodReviewRepository
import uk.ac.tees.mad.foodreview.utils.PreferenceManager
import kotlin.String

class FeedBackSubmissionViewModel(private val foodReviewRepository: FoodReviewRepository ,
    private val preferenceManager: PreferenceManager ):
ViewModel(){

    private val _feedBackUiState = MutableStateFlow(FeedbackSubmissionUiState())
    val feedBackUiState = _feedBackUiState.asStateFlow()

    init {
        resolveInitial()
    }

    private fun resolveInitial(){
        val foodName = preferenceManager.getFoodName()
        val foodReview = preferenceManager.getFoodReview()
        val foodRating = preferenceManager.getFoodRating()

        _feedBackUiState.update {
            it.copy(
                foodName = foodName?:"" ,
                reviewText = foodReview?:"" ,
                rating = foodRating
            )
        }
    }


    fun onFoodNameChange(foodName : String){
        _feedBackUiState.update {
            it.copy(
                foodName = foodName
            )
        }
        preferenceManager.setFoodName(foodName)
    }

    fun onReviewTextChange(review : String){
        _feedBackUiState.update {
            it.copy(
               reviewText = review
            )
        }
        preferenceManager.setFoodReview(review)
    }

    fun onRatingChange(rating : Int){
        _feedBackUiState.update {
            it.copy(
                rating = rating
            )
        }
        preferenceManager.setFoodRating(rating)
    }

    fun onSubmissionClick(){
        viewModelScope.launch {
            _feedBackUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val review = Review(
                foodName = _feedBackUiState.value.foodName ,
                reviewText = _feedBackUiState.value.reviewText ,
                rating = _feedBackUiState.value.rating
            )
            foodReviewRepository
                .submitReview(review)
                .onSuccess{
                    _feedBackUiState.update {
                        it.copy(
                            isLoading = false ,
                            isSubmissionSuccess = true ,
                            error = null
                        )
                    }
                }
                .onFailure { error->
                    _feedBackUiState.update {
                        it.copy(
                            isLoading = false ,
                            isSubmissionSuccess = false ,
                            error = error.message
                        )
                    }
                }
        }
    }

    fun reset(){
        _feedBackUiState.update {
            it.copy(
                foodName = "" ,
                reviewText = "" ,
                rating = 0 ,
            )
        }
        preferenceManager.setFoodName("")
        preferenceManager.setFoodReview("")
        preferenceManager.setFoodRating(0)
    }
}