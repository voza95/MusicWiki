package com.example.musicwiki.retrofit

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object AppClient {

    private var retrofit: Retrofit? = null


    val BASE_URL = "http://www.last.fm/api/auth/?api_key="
//    val BASE_URL = "http://ws.audioscrobbler.com/2.0/"

    fun getClient(context: Context): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                //val requestBuilder = original.newBuilder().addHeader("Content-type", "application/json; charset=utf-8")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(interceptor)

        val client = builder.build()
        var gson = GsonBuilder()
            .setLenient()
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        return (retrofit)!!
    }

}