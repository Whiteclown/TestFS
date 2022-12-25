package com.example.remote.data.mapper

import com.example.remote.data.dto.BankDto
import com.example.remote.data.dto.BinInfoDto
import com.example.remote.data.dto.CountryDto
import com.example.remote.data.dto.NumberDto
import com.example.remote.domain.entity.Bank
import com.example.remote.domain.entity.BinInfo
import com.example.remote.domain.entity.Country

fun BinInfoDto.toEntity() =
    BinInfo(
        bin = bin,
        number = number?.toEntity(),
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        country = country?.toEntity(),
        bank = bank?.toEntity(),
    )

fun BinInfo.toDto() =
    BinInfoDto(
        bin = bin,
        number = number?.toDto(),
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        country = country?.toDto(),
        bank = bank?.toDto(),
    )

fun NumberDto.toEntity() =
    com.example.remote.domain.entity.Number(
        length = length,
        luhn = luhn,
    )

fun com.example.remote.domain.entity.Number.toDto() =
    NumberDto(
        length = length,
        luhn = luhn,
    )

fun CountryDto.toEntity() =
    Country(
        numeric = numeric,
        alpha2 = alpha2,
        name = name,
        emoji = emoji,
        currency = currency,
        latitude = latitude,
        longitude = longitude,
    )

fun Country.toDto() =
    CountryDto(
        numeric = numeric,
        alpha2 = alpha2,
        name = name,
        emoji = emoji,
        currency = currency,
        latitude = latitude,
        longitude = longitude,
    )

fun BankDto.toEntity() =
    Bank(
        name = name,
        url = url,
        phone = phone,
        city = city,
    )

fun Bank.toDto() =
    BankDto(
        name = name,
        url = url,
        phone = phone,
        city = city,
    )