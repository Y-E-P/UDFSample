package com.pou.udfsample.domain.repo

import com.pou.udfsample.domain.FruitModel

class FruitsRepository(private val fruitsRemoteSource: FruitsRemoteSource) {

    suspend fun allFruitsList(): List<FruitModel> = fruitsRemoteSource.allFruitsList()
}