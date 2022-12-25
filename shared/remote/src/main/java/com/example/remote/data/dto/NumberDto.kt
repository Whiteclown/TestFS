package com.example.remote.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NumberDto(
    val length: Int? = null,
    val luhn: Boolean? = null,
)