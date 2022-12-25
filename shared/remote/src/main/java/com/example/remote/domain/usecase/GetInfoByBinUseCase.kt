package com.example.remote.domain.usecase

import com.example.remote.domain.repository.BinRepository
import javax.inject.Inject

class GetInfoByBinUseCase @Inject constructor(
    private val repository: BinRepository,
) {

    suspend operator fun invoke(bin: Int) =
        repository.getInfoByBin(bin)
}