package com.example.remote.data.datasource

import com.example.remote.domain.entity.BinInfo

interface BinLocalDataSource {

    suspend fun getBinsInfo(): List<BinInfo>

    suspend fun insertBinInfo(binInfoDto: BinInfo)
}