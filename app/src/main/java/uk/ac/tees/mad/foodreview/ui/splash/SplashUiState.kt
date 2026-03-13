package uk.ac.tees.mad.foodreview.ui.splash

sealed class SplashUiState {
    object Loading : SplashUiState()
    object NavigateToHome : SplashUiState()
    object NavigateToLogin : SplashUiState()
}