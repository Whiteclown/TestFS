package com.example.testfs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.remote.domain.usecase.GetInfoByBinUseCase
import com.example.testfs.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {

    fun bindNavController(navController: NavController) {
        navigator.bind(navController)
    }

    fun unbindNavController() {
        navigator.unbind()
    }
}