package com.example.history.presentation

import com.example.remote.domain.entity.BinInfo

interface HistoryRouter {

    fun routeToHome(binInfo: BinInfo)
}