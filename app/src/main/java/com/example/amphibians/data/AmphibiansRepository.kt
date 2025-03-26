package com.example.amphibians.data

import com.example.amphibians.model.AmphibiansData
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getData(): List<AmphibiansData>
}

class NetworkAmphibiansRepository(private val amphibiansApiService: AmphibiansApiService ): AmphibiansRepository{
    override suspend fun getData(): List<AmphibiansData> {
        return amphibiansApiService.getAmphibiansData()
    }
}