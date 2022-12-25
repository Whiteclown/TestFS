package com.example.home.presentation

import com.example.remote.domain.entity.BinInfo

sealed interface HomeState {

    object Loading : HomeState

    data class Loaded(
        val currentPage: Int,
        val binInfo: BinInfo?,
    ) : HomeState
}