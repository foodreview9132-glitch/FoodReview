package uk.ac.tees.mad.foodreview.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.foodreview.data.local.FoodReviewDao
import uk.ac.tees.mad.foodreview.data.local.toFoodReviewEntity
import uk.ac.tees.mad.foodreview.data.local.toReview
import uk.ac.tees.mad.foodreview.domain.model.Review
import uk.ac.tees.mad.foodreview.domain.repository.FoodReviewRepository

class FoodReviewRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val dao: FoodReviewDao
) : FoodReviewRepository {

    override fun fetchAllReview(): Flow<List<Review>> = callbackFlow {
        //emit cached data first
        val localJob = launch {
            dao.getReviewsList().collect { localReview ->
                trySend(localReview.map { it.toReview() })
            }
        }

        // listen to the firebase

        val listener = firebaseFirestore
            .collection(REVIEWS)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .limit(50)
            .addSnapshotListener { snapshot, error ->
                error?.let {
                    return@addSnapshotListener
                }
                val reviews = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(Review::class.java)?.copy(id = doc.id)
                } ?: emptyList()

                //update room here first

                launch {
                    dao.clearAll()
                    dao.insertReviews(reviews.map { it.toFoodReviewEntity() })
                }
            }
        awaitClose {
            listener.remove()
            localJob.cancel()
        }
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


/**
 * snapshot -->> it is a mechanism provided by firebase which keep on listening , and if
 * any changes occur it respond back and it is used for the realtime update.
 * in this project it is to be used to fetch the review list in realtime manner .
 * for snapshot to work on we have to register a listener to particular path.
 * callbackFlow is a way to convert the callback base api into the flow .
 */