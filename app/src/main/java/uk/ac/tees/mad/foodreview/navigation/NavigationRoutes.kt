package uk.ac.tees.mad.foodreview.navigation

sealed class NavigationRoutes (val route : String){
    object Reviews : NavigationRoutes("home")

    object DraftFeedback : NavigationRoutes("draft")
    object Login : NavigationRoutes("login")
    object SignUp : NavigationRoutes("signup")
    object Setting : NavigationRoutes("setting")
}