package uk.ac.tees.mad.foodreview.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.foodreview.domain.repository.AuthRepository
import uk.ac.tees.mad.foodreview.utils.mapAuthError

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _signupUiState = MutableStateFlow(SignUpUiState())
    val signupUiState: StateFlow<SignUpUiState> = _signupUiState.asStateFlow()

    fun onEmailChange(email : String) {
        _signupUiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun onPasswordChange(password : String) {
        _signupUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword : String) {
        _signupUiState.update {
            it.copy(
                confirmPassword = confirmPassword
            )
        }
    }

    fun onPasswordVisibility(value : Boolean){
        _signupUiState.update {
            it.copy(
                isPasswordVisible = value
            )
        }
    }

    fun onConfirmPasswordVisibility(value : Boolean){
        _signupUiState.update {
            it.copy(
                isConfirmPasswordVisible = value
            )
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _signupUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            authRepository.signUp(
                email = _signupUiState.value.email,
                password = _signupUiState.value.password
            )
                .onFailure { error ->
                    _signupUiState.update {
                        it.copy(
                            isLoading = false,
                            error = mapAuthError(error = error),
                            isSignupSuccess = false
                        )
                    }
                }
                .onSuccess { success ->
                    _signupUiState.update {
                        it.copy(
                            isLoading = false,
                            error = null,
                            isSignupSuccess = true
                        )
                    }
                }
        }
    }

}