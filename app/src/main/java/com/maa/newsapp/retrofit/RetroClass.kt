package com.maa.mvvm.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetroClass {
    val BASE_PATH="https://newsapi.org/"
    fun retrofitInstance():Retrofit{
        val okhttp=OkHttpClient().newBuilder().connectTimeout(60,TimeUnit.SECONDS).readTimeout(60,TimeUnit.SECONDS).writeTimeout(60,TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder().baseUrl(BASE_PATH).client(okhttp).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun apiService():APIService{
        return retrofitInstance().create(APIService::class.java)
    }
}