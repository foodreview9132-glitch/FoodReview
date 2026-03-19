package uk.ac.tees.mad.foodreview.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.foodreview.domain.model.Review
import uk.ac.tees.mad.foodreview.domain.repository.FoodReviewRepository

class FoodReviewRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : FoodReviewRepository {

    override fun fetchAllReview(): Flow<List<Review>> = callbackFlow {

        val listener = firebaseFirestore
            .collection(REVIEWS)
            .orderBy("createdAt" , Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot , error ->
                error?.let {
                    return@addSnapshotListener
                }
                val reviews = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(Review :: class.java)?.copy(id = doc.id)
                }?:emptyList()

                trySend(reviews)
            }
        awaitClose { listener.remove() }
    }

    override suspend fun submitReview(review: Review): Result<Unit> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: throw Exception("User ID is null")
            try {
                val reviewWithUser = review.copy(userId = userId)
                firebaseFirestore
                    .collection(REVIEWS)
                    .add(reviewWithUser)
                    .await()
            } catch (e: Exception) {
                throw e
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    companion object {
        const val USER = "user"
        const val REVIEWS = "reviews"
    }
}