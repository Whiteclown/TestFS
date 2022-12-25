package com.example.home.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.NoNetworkConnectionException
import com.example.core.StringResourcesProvider
import com.example.home.R
import com.example.remote.domain.entity.BinInfo
import com.example.remote.domain.usecase.GetInfoByBinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val router: HomeRouter,
    private val getInfoByBinUseCase: GetInfoByBinUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val stringResourcesProvider: StringResourcesProvider,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state
        get() = _state.asStateFlow()

    private val _actions: Channel<HomeAction> = Channel(Channel.BUFFERED)
    val actions: Flow<HomeAction> = _actions.receiveAsFlow()

    fun loadInfoByBin(bin: Int) {
        viewModelScope.launch {
            try {
                val binInfo = getInfoByBinUseCase(bin)

                _state.value = HomeState.Loaded(
                    currentPage = 1,
                    binInfo = binInfo,
                )
            } catch (e: Exception) {
                when (e) {

                    is HttpException -> _actions.send(HomeAction.ShowError(e.code().toString()))

                    is NoNetworkConnectionException -> _actions.send(HomeAction.ShowError(e.message))

                    else -> _actions.send(
                        HomeAction.ShowError(
                            stringResourcesProvider.getString(
                                R.string.unknown_error_text
                            )
                        )
                    )
                }
            }
        }
    }

    fun checkBinInfo() {
        if (_state.value is HomeState.Loading) {
            savedStateHandle.get<BinInfo>("binInfo")?.let {
                _state.value = HomeState.Loaded(
                    currentPage = 1,
                    binInfo = it,
                )
            }
        }
    }

    fun goToPreviousPage() {
        if (_state.value is HomeState.Loaded) {
            val content = _state.value as HomeState.Loaded
            _state.value = HomeState.Loaded(
                currentPage = content.currentPage - 1,
                binInfo = content.binInfo,
            )
        }
    }

    fun goToHistory() =
        router.routeToHistory()
}