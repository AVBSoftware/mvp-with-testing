package com.example.photogalearykotlin.network

import com.example.photogalearykotlin.model.album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {
    @GET("photos")
    fun getPhotosList(@Header("Authorization") clientid: String?): Call<List<album?>?>?
}