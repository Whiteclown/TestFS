package com.example.testfs.navigation

import androidx.navigation.NavController
import com.example.history.presentation.HistoryRouter
import com.example.home.presentation.HomeRouter
import com.example.home.ui.HomeFragment
import com.example.remote.domain.entity.BinInfo
import com.example.testfs.R

class Navigator : HomeRouter, HistoryRouter {

    private var navController: NavController? = null

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }

    override fun routeToHistory() {
        navController?.navigate(R.id.action_homeFragment_to_historyFragment)
    }

    override fun routeToHome(binInfo: BinInfo) {
        navController?.navigate(
            R.id.action_historyFragment_to_homeFragment,
            HomeFragment.createBundle(binInfo)
        )
    }
}