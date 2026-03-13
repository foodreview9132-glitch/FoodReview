package uk.ac.tees.mad.foodreview.utils

import android.content.Context
import androidx.core.content.edit


class PreferenceManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    companion object{
        const val IS_LOGGED_IN = "isLoggedIn"
    }

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(IS_LOGGED_IN ,false)
    }

    fun setLoggedIn(value : Boolean){
        sharedPreferences.edit {
            putBoolean(IS_LOGGED_IN , value)
        }
    }
}