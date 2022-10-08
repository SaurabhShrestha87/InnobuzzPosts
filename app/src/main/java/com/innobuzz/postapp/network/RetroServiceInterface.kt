package com.innobuzz.postapp.network

import com.innobuzz.postapp.model.DataPost
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("posts")
    fun getDataFromAPI(): Call<List<DataPost>>
}