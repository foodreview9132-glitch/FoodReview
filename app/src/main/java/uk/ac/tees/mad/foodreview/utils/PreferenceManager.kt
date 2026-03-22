package uk.ac.tees.mad.foodreview.utils

import android.content.Context
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.ac.tees.mad.foodreview.domain.model.Review


class PreferenceManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    companion object{
        const val IS_LOGGED_IN = "isLoggedIn"
        const val USER_EMAIL = "user_email"
        const val LAST_LOGIN = "last_login"
        const val IS_DARK_MODE = "is_dark_mode"
        const val FOOD_NAME = "food_name"
        const val FOOD_REVIEW = "food_review"
        const val FOOD_RATING = "food_rating"
    }

    private val _isDarkModeFlow = MutableStateFlow(getDarkMode())
    val isDarkModeFLow = _isDarkModeFlow.asStateFlow()

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener { _ , key ->
            if(key == IS_DARK_MODE){
                _isDarkModeFlow.value = getDarkMode()
            }
        }
    }

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(IS_LOGGED_IN ,false)
    }

    fun setLoggedIn(value : Boolean){
        sharedPreferences.edit {
            putBoolean(IS_LOGGED_IN , value)
        }
    }

    fun setEmail(email : String){
        sharedPreferences.edit{
            putString(USER_EMAIL , email)
        }
    }

    fun setLastLogin(lastLogin : String){
        sharedPreferences.edit{
            putString(
                LAST_LOGIN , lastLogin
            )
        }
    }

    fun setDarkMode(value : Boolean){
        sharedPreferences.edit {
            putBoolean(
                IS_DARK_MODE , value
            )
        }
    }

    fun getEmail(): String{
        return sharedPreferences.getString(USER_EMAIL , "") ?: ""
    }

    fun getLastLogin(): String{
        return sharedPreferences.getString(LAST_LOGIN , "") ?: ""
    }

    fun getDarkMode(): Boolean {
        return sharedPreferences.getBoolean(IS_DARK_MODE, false)
    }

    fun setFoodName(name : String){
        sharedPreferences.edit{
            putString(FOOD_NAME , name)
        }
    }

    fun getFoodName() : String? {
        return sharedPreferences.getString(FOOD_NAME ,"")
    }

    fun setFoodReview(review: String){
        sharedPreferences.edit{
            putString(FOOD_REVIEW ,review)
        }
    }

    fun getFoodReview(): String?{
        return sharedPreferences.getString(FOOD_REVIEW ,"")
    }

    fun setFoodRating(rating :Int){
        sharedPreferences.edit {
            putInt(FOOD_RATING , rating)
        }
    }

    fun getFoodRating():Int{
        return sharedPreferences.getInt(FOOD_RATING , 0)
    }
}


/**
 * this is preference manager class which stores key value pair .
 * it is used to store the small data .
 * for theme changing i am using a value isDarkMode and it is being emitted as flow and have registered a listener which will keep
 * on listening the changes in the sharedPreference and if any change will occur it will react and emit latest value of the isDarkMode.
 */