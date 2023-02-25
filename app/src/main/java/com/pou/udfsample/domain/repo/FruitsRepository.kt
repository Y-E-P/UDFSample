package com.pou.udfsample.domain.repo

import com.pou.udfsample.domain.FruitModel
import com.pou.udfsample.ui.main.ServiceLocator

class FruitsRepository(private val fruitsRemoteSource: FruitsRemoteSource) {

    suspend fun allFruitsList(): List<FruitModel> = fruitsRemoteSource.allFruitsList()
    suspend fun checkOkhttp(): String? = ServiceLocator.request("https://fruityvice.com/api/fruit/all")
}