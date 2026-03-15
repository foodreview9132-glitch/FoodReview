package uk.ac.tees.mad.foodreview.ui.signup

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.foodreview.FoodReviewApp
import uk.ac.tees.mad.foodreview.ui.components.CustomTextField
import uk.ac.tees.mad.foodreview.ui.theme.Dimens
import uk.ac.tees.mad.foodreview.utils.viewModelFactory

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel<SignUpViewModel>(
        factory = viewModelFactory {
            SignUpViewModel(
                authRepository = FoodReviewApp.appModule.authRepository
            )
        }
    ) ,
    onLoginClick:()-> Unit ,
    onNavigateToHome: () -> Unit
){

    val uiState by viewModel.signupUiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isSignupSuccess) {
        if(uiState.isSignupSuccess){
            onNavigateToHome()
        }
    }

    SignUpScreenContent(
        email = uiState.email ,
        password = uiState.password ,
        confirmPassword = uiState.confirmPassword ,
        isPasswordVisible = uiState.isPasswordVisible,
        isConfirmPasswordVisible = uiState.isConfirmPasswordVisible ,
        error  = uiState.error ,
        isLoading = uiState.isLoading,
        isButtonEnabled = uiState.isButtonEnabled,
        onEmailChange = viewModel::onEmailChange ,
        onPasswordChange = viewModel::onPasswordChange ,
        onConfirmPasswordChange = viewModel::onConfirmPasswordChange ,
        onPasswordVisibility = viewModel::onPasswordVisibility,
        onConfirmPasswordVisibility = viewModel::onConfirmPasswordVisibility,
        onSignUpClick = viewModel::onSignUpClick  ,
        onLoginClick = onLoginClick
    )
}


@Composable
fun SignUpScreenContent(
    email: String,
    password: String,
    confirmPassword: String,
    isPasswordVisible: Boolean,
    isConfirmPasswordVisible: Boolean,
    isLoading :Boolean ,
    isButtonEnabled :Boolean ,
    error: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibility: (Boolean) -> Unit,
    onSignUpClick: () -> Unit,
    onConfirmPasswordVisibility: (Boolean) -> Unit,
    onLoginClick: () -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.ScreenHorizontal)
                .imePadding()
                .align(Alignment.Center),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = Dimens.ElevationMedium
            ),
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .background(Color.Transparent),
                verticalArrangement = Arrangement.spacedBy(Dimens.Medium)
            ){


                Text(
                    text = "Sign up to continue",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f) ,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )


                CustomTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    placeholder = "test@gmail.com",
                    label = "Email",
                    icon = Icons.Default.Email,
                )

                CustomTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    isPasswordVisible = isPasswordVisible,
                    placeholder = "password",
                    label = "Password",
                    isPasswordField = true,
                    icon = when (isPasswordVisible) {
                        true -> Icons.Default.Visibility
                        false -> Icons.Default.VisibilityOff
                    },
                    onIconClick = onPasswordVisibility
                )

                CustomTextField(
                    value = confirmPassword,
                    onValueChange = onConfirmPasswordChange,
                    isPasswordVisible = isConfirmPasswordVisible,
                    placeholder = "confirm password",
                    label = "confirm password",
                    isPasswordField = true,
                    icon = when (isConfirmPasswordVisible) {
                        true -> Icons.Default.Visibility
                        false -> Icons.Default.VisibilityOff
                    },
                    onIconClick = onConfirmPasswordVisibility
                )

                Button(
                    onClick = onSignUpClick,
                    enabled = isButtonEnabled && !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
                    )
                ) {
                    when{
                        isLoading ->{
                            CircularProgressIndicator(
                                modifier =  Modifier.size(24.dp) ,
                                color = MaterialTheme.colorScheme.primary ,
                            )
                        }else->{
                        Text("Sign Up")
                        }
                    }
                }

                Text(
                    text = "Already have an account? Login",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f) ,
                    modifier = Modifier.align(Alignment.CenterHorizontally).clickable{
                       onLoginClick()
                    }
                )

                error?.let {
                    Text(
                        text = it ,
                        color = MaterialTheme.colorScheme.error ,
                        modifier =  Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

        }
    }


}


@Preview(showBackground = true)
@Composable
fun SignUpPreview(){
    SignUpScreenContent(
        email = "",
        password = "",
        confirmPassword = "",
        isPasswordVisible = false,
        isConfirmPasswordVisible = false,
        error = null,
        onEmailChange = {},
        onPasswordChange = {},
        onConfirmPasswordChange = {},
        onPasswordVisibility = {},
        onSignUpClick = {},
        onConfirmPasswordVisibility = {},
        onLoginClick = {} ,
        isLoading = true ,
        isButtonEnabled = true
    )
}
