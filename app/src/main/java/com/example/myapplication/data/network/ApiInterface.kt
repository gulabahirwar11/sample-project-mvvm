package com.example.myapplication.data.network

import com.example.myapplication.data.reposiotry.flickr.UpstoxResponseEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun getUpstoxItems(@Url url: String) : Single<UpstoxResponseEntity>
}