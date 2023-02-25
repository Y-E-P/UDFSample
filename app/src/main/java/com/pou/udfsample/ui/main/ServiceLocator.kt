package com.pou.udfsample.ui.main

import android.annotation.SuppressLint
import android.content.Context
import com.pou.udfsample.domain.repo.FruitsRemoteSource
import com.pou.udfsample.domain.repo.FruitsRepository
import com.pou.udfsample.domain.repo.FruitsService
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@SuppressLint("StaticFieldLeak")
object ServiceLocator {

    lateinit var context: Context

    private val httpClient: OkHttpClient by lazy {
        if (!::context.isInitialized) throw NullPointerException("Context must be initialized")
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

    suspend fun request(url: String): String? = suspendCoroutine {
        val request: Request = Request.Builder().url(url).build()
        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                it.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                it.resume(response.body()?.string())
            }
        })
    }

}