package uk.ac.tees.mad.foodreview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.foodreview.navigation.AppNavigationHost
import uk.ac.tees.mad.foodreview.navigation.NavigationRoutes
import uk.ac.tees.mad.foodreview.ui.splash.SplashUiState
import uk.ac.tees.mad.foodreview.ui.splash.SplashViewModel
import uk.ac.tees.mad.foodreview.ui.theme.FoodReviewTheme
import uk.ac.tees.mad.foodreview.utils.viewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val splashViewModel : SplashViewModel by viewModels {
            viewModelFactory {
                SplashViewModel(
                    preferenceManager = FoodReviewApp.appModule.preferenceManager
                )
            }
        }
        splashScreen.setKeepOnScreenCondition {
            splashViewModel.splashUiState.value is SplashUiState.Loading
        }

        setContent {
            val navController = rememberNavController()
            val startDestination = when(splashViewModel
                .splashUiState
                .collectAsState()
                .value){
                SplashUiState.NavigateToHome -> NavigationRoutes.Home
                SplashUiState.NavigateToLogin -> NavigationRoutes.Login
                else -> null
            }

            startDestination?.let{
                FoodReviewTheme {
                    AppNavigationHost(
                        startDestination = startDestination.route ,
                        navController = navController
                    )
                }
            }
        }
    }
}