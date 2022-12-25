package com.example.remote.domain.repository

import com.example.remote.domain.entity.BinInfo

interface BinRepository {

    suspend fun getInfoByBin(bin: Int): BinInfo

    suspend fun getBinsInfo(): List<BinInfo>
}