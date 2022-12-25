package com.example.home.presentation

sealed interface HomeAction {

    data class ShowError(
        val message: String,
    ) : HomeAction
}