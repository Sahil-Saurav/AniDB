package com.example.anidb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstance {
    private const val baseUrl = "https://api.jikan.moe/v4/"

    fun getInstance():Retrofit{
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
    val api:JikanAPI = getInstance().create(JikanAPI::class.java)
}