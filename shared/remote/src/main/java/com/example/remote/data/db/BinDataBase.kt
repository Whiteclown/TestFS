package com.example.remote.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.remote.data.dao.BinDao
import com.example.remote.data.dto.BinInfoDto

@Database(entities = [BinInfoDto::class], version = 1, exportSchema = false)
abstract class BinDataBase : RoomDatabase() {

    abstract fun binDao(): BinDao

    companion object {

        const val TABLE_BIN_NAMES = "TableBinNames"
    }
}