package com.example.remote.data.datasource

import com.example.remote.data.dao.BinDao
import com.example.remote.data.mapper.toDto
import com.example.remote.data.mapper.toEntity
import com.example.remote.domain.entity.BinInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BinLocalDataSourceImpl @Inject constructor(
    private val dao: BinDao,
) : BinLocalDataSource {

    override suspend fun getBinsInfo(): List<BinInfo> =
        withContext(Dispatchers.IO) {
            dao.getBinsInfo().map { it.toEntity() }
        }

    override suspend fun insertBinInfo(binInfo: BinInfo) {
        withContext(Dispatchers.IO) {
            dao.insertBinInfo(binInfo.toDto())
        }
    }
}