package com.rama.quotes.interfaces.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.rama.quotes.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val API_URL = BuildConfig.API_URL
    private var retrofitData: Retrofit? = null

    private val okHttpClient =
        OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).readTimeout(3, TimeUnit.SECONDS)
            .build()

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun Retrofit(): Retrofit {
        if (retrofitData == null) {
            retrofitData = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofitData!!
    }

}