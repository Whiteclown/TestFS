package com.example.remote.data.repository

import com.example.remote.data.datasource.BinLocalDataSource
import com.example.remote.data.datasource.BinRemoteDataSource
import com.example.remote.domain.entity.BinInfo
import com.example.remote.domain.repository.BinRepository
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    private val localDataSource: BinLocalDataSource,
    private val remoteDataSource: BinRemoteDataSource,
) : BinRepository {

    override suspend fun getInfoByBin(bin: Int): BinInfo {
        val binInfo = remoteDataSource.getInfoByBin(bin).copy(bin = bin.toLong())
        localDataSource.insertBinInfo(binInfo)
        return binInfo
    }

    override suspend fun getBinsInfo(): List<BinInfo> =
        localDataSource.getBinsInfo()
}