package uk.ac.tees.mad.foodreview.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.foodreview.domain.model.Review

interface FoodReviewRepository {

  fun fetchAllReview(): Flow<List<Review>>
   suspend fun submitReview(review: Review): Result<Unit>
}