package uk.ac.tees.mad.foodreview.ui.setting

data class SettingUiState(
    val userEmail : String ="",
    val lastLogin : String ="",
    val isLogoutSuccess : Boolean = false,
    val isLoading : Boolean = false,
    val isDarkMode : Boolean = false,
    val error :String? = null
)