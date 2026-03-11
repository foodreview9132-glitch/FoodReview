package uk.ac.tees.mad.foodreview.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
fun <VM : ViewModel> viewModelFactory(
    initializer: () -> VM
): ViewModelProvider.Factory {

    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return initializer() as T
        }
    }
}


/**
 *how to inject it -->>>>>>>>>>>
 * val viewmodel = viewModel <MainViewModel>(
 * factory = viewModelFactory {
 * MainViewModel(MyApp.appModule.authRepository)
 * }
 * )
 */