package com.example.history.presentation

import com.example.remote.domain.entity.BinInfo

sealed interface HistoryState {

    object Loading : HistoryState

    data class Loaded(
        val bins: List<BinInfo>
    ) : HistoryState
}