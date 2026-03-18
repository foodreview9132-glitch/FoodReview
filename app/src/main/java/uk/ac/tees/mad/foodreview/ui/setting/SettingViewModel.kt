package uk.ac.tees.mad.foodreview.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.foodreview.domain.repository.AuthRepository
import uk.ac.tees.mad.foodreview.utils.PreferenceManager

class SettingViewModel(private val authRepository: AuthRepository ,
    private val preference: PreferenceManager)
    : ViewModel() {

        private val _settingUiState = MutableStateFlow(SettingUiState())
    val settingUiState = _settingUiState.asStateFlow()


    init {
        resolveInitial()
    }

    fun onToggle(value : Boolean){
        preference.setDarkMode(value)
        _settingUiState.update {
            it.copy(
                isDarkMode = value
            )
        }
    }

    private fun resolveInitial(){
        _settingUiState.update {
            it.copy(
                userEmail = preference.getEmail(),
                lastLogin = preference.getLastLogin(),
                isDarkMode = preference.getDarkMode()
            )
        }
    }

    fun onLogoutClick(){
        viewModelScope.launch {
            _settingUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            authRepository
                .signOut()
                .onSuccess {
                    _settingUiState.update {
                        it.copy(
                            isLoading = false ,
                            isLogoutSuccess = true ,
                        )
                    }
                    preference.setDarkMode(false)
                    preference.setLoggedIn(false)
                    preference.setEmail("")
                    preference.setLastLogin("")
                }
                .onFailure {error->
                    _settingUiState.update {
                        it.copy(
                            isLoading = false ,
                            error = error.message
                        )
                    }
                }
        }
    }
}