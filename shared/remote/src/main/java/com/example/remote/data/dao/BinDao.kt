package com.example.remote.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.remote.data.db.BinDataBase
import com.example.remote.data.dto.BinInfoDto

@Dao
interface BinDao {

    @Query("SELECT * FROM ${BinDataBase.TABLE_BIN_NAMES}")
    suspend fun getBinsInfo(): List<BinInfoDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinInfo(binInfoDto: BinInfoDto)
}