package uk.ac.tees.mad.foodreview.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uk.ac.tees.mad.foodreview.domain.model.User
import uk.ac.tees.mad.foodreview.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : AuthRepository {
    override suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val result = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            val userId = result.user?.uid ?: throw Exception("User ID is null")
            try {
                firebaseFirestore
                    .collection(USER)
                    .document(userId)
                    .set(User(userEmail = email))
                    .await()
            } catch (e: Exception) {
                result.user?.delete()
                throw e
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            firebaseAuth
                .signInWithEmailAndPassword(email , password)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            firebaseAuth
                .signOut()
            Result.success(Unit)
        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    companion object {
        const val USER = "user"
    }
}


/**
 * SignUp() function will be responsible for registering new user and storing email at firebase
 * firestore if any exception it throws then no user creation will take place.
 */