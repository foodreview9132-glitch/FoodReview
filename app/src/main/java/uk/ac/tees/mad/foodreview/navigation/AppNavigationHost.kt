package uk.ac.tees.mad.foodreview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.foodreview.ui.feedback_submission.FeedbackSubmissionScreen
import uk.ac.tees.mad.foodreview.ui.login.LoginScreen
import uk.ac.tees.mad.foodreview.ui.reviews.ReviewListScreen
import uk.ac.tees.mad.foodreview.ui.setting.SettingScreen
import uk.ac.tees.mad.foodreview.ui.signup.SignUpScreen

@Composable
fun AppNavigationHost(
    startDestination: String,
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    NavHost(
        startDestination = startDestination ,
        navController = navController ,
        modifier = modifier
    ) {

        composable(route = NavigationRoutes.DraftFeedback.route){
            FeedbackSubmissionScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavigationRoutes.Reviews.route){
            ReviewListScreen(
                onSettingClick = {
                    navController.navigate(NavigationRoutes.Setting.route)
                },
                onDraftFeedbackClick = {
                    navController.navigate(NavigationRoutes.DraftFeedback.route)
                },
            )
        }
        composable(route = NavigationRoutes.Login.route){
            LoginScreen(
                onSignUpClick = {
                    navController.navigate(NavigationRoutes.SignUp.route)
                } ,
                onNavigateToHome = {
                    navController.navigate(NavigationRoutes.Reviews.route){
                        popUpTo(NavigationRoutes.Login.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = NavigationRoutes.SignUp.route){
            SignUpScreen(
                onLoginClick = {
                    navController.popBackStack()
                } ,
                onNavigateToHome = {
                    navController.navigate(NavigationRoutes.Reviews.route){
                        popUpTo(NavigationRoutes.SignUp.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = NavigationRoutes.Setting.route){
            SettingScreen(
                onBackClick = {
                    navController.popBackStack()
                } ,
                onNavigateToLogin = {
                    navController.navigate(NavigationRoutes.Login.route){
                        popUpTo(NavigationRoutes.Setting.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}