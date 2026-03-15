package uk.ac.tees.mad.foodreview.ui.signup

import uk.ac.tees.mad.foodreview.utils.isValidEmail

data class SignUpUiState(
    val email : String = "" ,
    val password : String = "" ,
    val confirmPassword : String = "" ,
    val isLoading : Boolean = false ,
    val isPasswordVisible : Boolean = false ,
    val isConfirmPasswordVisible : Boolean = false ,
    val isSignupSuccess : Boolean = false ,
    val error : String ? = null ,
){
    val isButtonEnabled :Boolean
        get() = isValidEmail(email) &&
                password.length >=8 &&
                password == confirmPassword
}