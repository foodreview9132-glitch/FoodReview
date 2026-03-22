package uk.ac.tees.mad.foodreview.ui.feedback_submission

data class FeedbackSubmissionUiState(
    val foodName : String = "",
    val reviewText : String = "",
    val rating :Int  = 0,
    val isLoading : Boolean = false ,
    val error : String? = null ,
    val isSubmissionSuccess : Boolean = false
){
    val isSubmitEnable : Boolean
        get() = foodName.isNotEmpty() && reviewText.isNotEmpty() && rating >0

    val isReviewEnable : Boolean
        get() = foodName.isNotEmpty() && reviewText.isNotEmpty()
}