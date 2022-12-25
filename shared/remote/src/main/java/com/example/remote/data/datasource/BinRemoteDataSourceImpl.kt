package com.example.remote.data.datasource

import com.example.remote.data.api.BinApi
import com.example.remote.data.mapper.toEntity
import com.example.remote.domain.entity.BinInfo
import javax.inject.Inject

class BinRemoteDataSourceImpl @Inject constructor(
    private val api: BinApi,
) : BinRemoteDataSource {

    override suspend fun getInfoByBin(bin: Int): BinInfo =
        api.getInfoByBin(bin).toEntity()
}