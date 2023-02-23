package com.pou.udfsample.ui.main

import android.content.Context
import com.pou.udfsample.domain.repo.FruitsRemoteSource
import com.pou.udfsample.domain.repo.FruitsRepository
import com.pou.udfsample.domain.repo.FruitsService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {

    lateinit var context: Context

    private val httpClient: OkHttpClient by lazy {
        if(!::context.isInitialized) throw NullPointerException("Context must be initialized")
        OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, 10 * 1024 * 1024))
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://fruityvice.com/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val fruitsService: FruitsService by lazy { retrofit.create(FruitsService::class.java) }

    val fruitsRemoteSource: FruitsRemoteSource by lazy { FruitsRemoteSource(fruitsService) }

    val repository: FruitsRepository by lazy {
        FruitsRepository(fruitsRemoteSource)
    }
}