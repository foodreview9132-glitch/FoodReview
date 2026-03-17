package uk.ac.tees.mad.foodreview.navigation

sealed class NavigationRoutes (val route : String){
    object Home : NavigationRoutes("home")
    object Login : NavigationRoutes("login")
    object SignUp : NavigationRoutes("signup")
    object Setting : NavigationRoutes("setting")
}