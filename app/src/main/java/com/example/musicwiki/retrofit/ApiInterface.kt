package com.example.musicwiki.retrofit


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("")
    fun getHistoryList(
        @Query("demo") demo:String
    ): Call<Any>
}