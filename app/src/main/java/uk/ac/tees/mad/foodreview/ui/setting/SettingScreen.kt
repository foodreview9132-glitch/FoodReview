package uk.ac.tees.mad.foodreview.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.foodreview.FoodReviewApp
import uk.ac.tees.mad.foodreview.ui.setting.component.SettingTopBar
import uk.ac.tees.mad.foodreview.ui.setting.component.ThemeSettingCard
import uk.ac.tees.mad.foodreview.ui.setting.component.UserProfileCard
import uk.ac.tees.mad.foodreview.ui.theme.Dimens
import uk.ac.tees.mad.foodreview.utils.viewModelFactory

@Composable
fun SettingScreen(
    onBackClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: SettingViewModel = viewModel<SettingViewModel>(
        factory = viewModelFactory {
            SettingViewModel(
                authRepository = FoodReviewApp.appModule.authRepository,
                preference = FoodReviewApp.appModule.preferenceManager
            )
        }
    )
) {
    val uiState by viewModel.settingUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isLogoutSuccess) {
        if (uiState.isLogoutSuccess) {
            onNavigateToLogin()
        }
    }


    SettingScreenContent(
        onBackClick = onBackClick,
        userEmail = uiState.userEmail,
        lastLogin = uiState.lastLogin,
        isDarkMode = uiState.isDarkMode,
        isLoading = uiState.isLoading,
        onToggleMode = viewModel::onToggle,
        onLogoutClick = viewModel::onLogoutClick
    )
}

@Composable
fun SettingScreenContent(
    onBackClick: () -> Unit,
    userEmail: String,
    lastLogin: String,
    isDarkMode: Boolean,
    onToggleMode: (Boolean) -> Unit,
    onLogoutClick: () -> Unit,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()) {
            SettingTopBar(
                onBackClick = onBackClick
            )

            UserProfileCard(
                email = userEmail,
                lastLogin = lastLogin,
                modifier = Modifier.padding(horizontal = Dimens.ScreenHorizontal)
            )

            ThemeSettingCard(
                modifier = Modifier.padding(horizontal = Dimens.ScreenHorizontal),
                isDarkMode = isDarkMode,
                onToggle = onToggleMode
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )
            OutlinedButton(
                onClick = onLogoutClick,
                enabled = !isLoading
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    else -> {
                        Text(
                            text = "Logout"
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingPreview() {
    SettingScreenContent(
        onBackClick = {},
        userEmail = "james.iredell@examplepetstore.com",
        lastLogin = "",
        isDarkMode = false,
        onToggleMode = {},
        onLogoutClick = {},
        isLoading = false
    )
}