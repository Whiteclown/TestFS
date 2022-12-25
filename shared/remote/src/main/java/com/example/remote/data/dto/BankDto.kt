package com.example.remote.data.dto

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
data class BankDto(
    @ColumnInfo(name = "bank_name") val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null,
)