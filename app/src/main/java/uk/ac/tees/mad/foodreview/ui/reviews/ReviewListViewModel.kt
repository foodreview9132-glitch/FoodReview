package uk.ac.tees.mad.foodreview.ui.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.foodreview.domain.repository.FoodReviewRepository

class ReviewListViewModel(private val foodReviewRepository: FoodReviewRepository) : ViewModel() {
    private val _reviewListUiState = MutableStateFlow(ReviewListUiState())
    val uiState = _reviewListUiState.asStateFlow()

    init {
       observeReviews()
    }

    private fun observeReviews() {
        viewModelScope.launch {
            foodReviewRepository
                .fetchAllReview()
                .onStart {
                    _reviewListUiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                .catch { e ->
                    _reviewListUiState.update {
                        it.copy(
                            isLoading = false,
                            error = e.message
                        )
                    }
                }
                .collect { list ->
                    _reviewListUiState.update {
                        it.copy(
                            isLoading = false,
                            list = list,
                            error = null,
                        )
                    }
                }
        }
    }
}