package com.pou.udfsample.domain.repo

import com.pou.udfsample.domain.FruitModel
import retrofit2.Response
import retrofit2.http.GET

interface FruitsService {

    @GET("fruit/all")
    suspend fun allFruitsList(): Response<List<FruitModel>>

    @GET("fruit/all")
    suspend fun getFruit(id: String): Response<FruitModel>
}