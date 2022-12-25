package com.example.remote.domain.usecase

import com.example.remote.domain.repository.BinRepository
import javax.inject.Inject

class GetBinsInfoUseCase @Inject constructor(
    private val repository: BinRepository,
) {

    suspend operator fun invoke() =
        repository.getBinsInfo()
}