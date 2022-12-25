package com.example.core

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringResourcesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getString(@StringRes stringResId: Int): String =
        context.getString(stringResId)
}