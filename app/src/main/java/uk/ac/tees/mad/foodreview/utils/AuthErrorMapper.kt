package uk.ac.tees.mad.foodreview.utils

import android.util.Patterns
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

fun mapAuthError(error : Throwable): String{
    return when(error){
        is FirebaseAuthInvalidCredentialsException -> "Invalid Email or Password"
        is FirebaseAuthInvalidUserException -> "User Does not exist"
        is FirebaseAuthUserCollisionException -> "User Already Exist"
        is FirebaseAuthException -> error.message ?: "Unknown Error"
        else -> "some thing went wrong please try again"
    }
}


fun isValidEmail(email :String):Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

/**
 * AuthErrorMapper -->> it is a custom function for mapping the error to readable string
 * it takes the throwable and then convert it into the readable string by matching with
 * different exceptions
 */