package uk.ac.tees.mad.foodreview.ui.login

import uk.ac.tees.mad.foodreview.utils.isValidEmail

data class LoginUiState(
    val email :String = "" ,
    val password : String = "" ,
    val isLoading : Boolean = false ,
    val isLoginSuccess : Boolean = false ,
    val isPasswordVisible : Boolean = false ,
    val error : String ? = null
){
    val isButtonEnabled :Boolean
        get() = isValidEmail(email) &&
                password.length >=8
}