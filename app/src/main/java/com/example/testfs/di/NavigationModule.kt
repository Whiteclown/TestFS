package com.example.testfs.di

import com.example.history.presentation.HistoryRouter
import com.example.home.presentation.HomeRouter
import com.example.testfs.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator =
        Navigator()

    @Provides
    @Singleton
    fun provideHomeRouter(navigator: Navigator): HomeRouter =
        navigator

    @Provides
    @Singleton
    fun provideHistoryRouter(navigator: Navigator): HistoryRouter =
        navigator
}