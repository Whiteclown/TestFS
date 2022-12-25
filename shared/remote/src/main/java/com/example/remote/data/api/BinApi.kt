package com.example.remote.data.api

import com.example.remote.data.dto.BinInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {

    @GET("/{bin}")
    suspend fun getInfoByBin(@Path("bin") bin: Int): BinInfoDto
}