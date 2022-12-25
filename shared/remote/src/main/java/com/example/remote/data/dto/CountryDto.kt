package com.example.remote.data.dto

import androidx.room.ColumnInfo
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    val numeric: String? = null,
    val alpha2: String? = null,
    @ColumnInfo(name = "country_name") val name: String?,
    val emoji: String? = null,
    val currency: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
)