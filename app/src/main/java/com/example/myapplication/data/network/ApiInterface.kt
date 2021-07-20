package com.example.myapplication.data.network

import com.example.myapplication.data.reposiotry.flickr.UpstoxResponseEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiInterface {
    @GET("v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    fun getUpstoxItems() : Single<UpstoxResponseEntity>
}