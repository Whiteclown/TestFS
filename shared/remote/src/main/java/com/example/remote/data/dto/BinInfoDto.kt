package com.example.remote.data.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.remote.data.db.BinDataBase
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = BinDataBase.TABLE_BIN_NAMES)
data class BinInfoDto(
    @PrimaryKey(autoGenerate = false)
    val bin: Long = 0,
    @Embedded val number: NumberDto?,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    @Embedded val country: CountryDto?,
    @Embedded val bank: BankDto?,
)