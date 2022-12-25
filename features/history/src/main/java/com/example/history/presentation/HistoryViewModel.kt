package com.example.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remote.domain.entity.BinInfo
import com.example.remote.domain.usecase.GetBinsInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val router: HistoryRouter,
    private val getBinsInfoUseCase: GetBinsInfoUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<HistoryState>(HistoryState.Loading)
    val state
        get() = _state.asStateFlow()

    fun loadHistory() {
        if (_state.value is HistoryState.Loading) {
            viewModelScope.launch {
                val bins = getBinsInfoUseCase()

                _state.value = HistoryState.Loaded(
                    bins = bins
                )
            }
        }
    }

    fun openBinInfo(binInfo: BinInfo) =
        router.routeToHome(binInfo)
}