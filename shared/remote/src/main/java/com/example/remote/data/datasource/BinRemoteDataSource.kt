package com.example.remote.data.datasource

import com.example.remote.domain.entity.BinInfo

interface BinRemoteDataSource {

    suspend fun getInfoByBin(bin: Int): BinInfo
}