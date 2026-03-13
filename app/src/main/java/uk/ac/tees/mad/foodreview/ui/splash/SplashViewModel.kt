package uk.ac.tees.mad.foodreview.ui.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.ac.tees.mad.foodreview.utils.PreferenceManager

class SplashViewModel(private val preferenceManager: PreferenceManager): ViewModel() {
    private val _splashUiState : MutableStateFlow<SplashUiState> = MutableStateFlow(SplashUiState.Loading)
    val splashUiState = _splashUiState.asStateFlow()

    init {
        resolveSplash()
    }

    private fun resolveSplash(){
        when{
            preferenceManager.isLoggedIn() ->{
                _splashUiState.value = SplashUiState.NavigateToHome
            }
            else->{
                _splashUiState.value = SplashUiState.NavigateToLogin
            }
        }
    }
}