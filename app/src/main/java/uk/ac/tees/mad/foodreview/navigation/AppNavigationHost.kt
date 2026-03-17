package uk.ac.tees.mad.foodreview.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.foodreview.ui.home.HomeScreen
import uk.ac.tees.mad.foodreview.ui.login.LoginScreen
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

        composable(route = NavigationRoutes.Home.route){
            HomeScreen()
        }
        composable(route = NavigationRoutes.Login.route){
            LoginScreen(
                onSignUpClick = {
                    navController.navigate(NavigationRoutes.SignUp.route)
                } ,
                onNavigateToHome = {
                    navController.navigate(NavigationRoutes.Home.route){
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
                    navController.navigate(NavigationRoutes.Home.route){
                        popUpTo(NavigationRoutes.SignUp.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = NavigationRoutes.Setting.route){

        }
    }
}