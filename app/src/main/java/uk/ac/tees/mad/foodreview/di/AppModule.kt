package uk.ac.tees.mad.foodreview.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.foodreview.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.foodreview.data.repository.FoodReviewRepositoryImpl
import uk.ac.tees.mad.foodreview.domain.repository.AuthRepository
import uk.ac.tees.mad.foodreview.domain.repository.FoodReviewRepository
import uk.ac.tees.mad.foodreview.utils.PreferenceManager

interface  AppModule {
    val firebaseAuth : FirebaseAuth
    val firebaseFirestore : FirebaseFirestore
    val authRepository : AuthRepository
    val preferenceManager : PreferenceManager
    val foodReviewRepository : FoodReviewRepository
}

class AppModuleImpl(private val context : Context)
    : AppModule{
    override val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    override val firebaseFirestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(
            firebaseAuth = firebaseAuth ,
            firebaseFirestore = firebaseFirestore ,
            preferenceManager = preferenceManager
        )
    }
    override val preferenceManager: PreferenceManager by lazy {
        PreferenceManager(context)
    }
    override val foodReviewRepository: FoodReviewRepository by lazy {
        FoodReviewRepositoryImpl(
            firebaseAuth = firebaseAuth ,
            firebaseFirestore = firebaseFirestore
        )
    }

}