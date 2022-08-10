package com.pou.udfsample.domain.repo

import com.pou.udfsample.domain.FruitModel
import java.net.UnknownHostException

class FruitsRemoteSource(private val fruitsService: FruitsService) {

    suspend fun allFruitsList(): List<FruitModel> {
        return try {
            val response = fruitsService.allFruitsList()
            when {
                response.isSuccessful -> response.body()!!
                else -> emptyList()
            }
        } catch (e: UnknownHostException) {
            emptyList()
        }

    }
}