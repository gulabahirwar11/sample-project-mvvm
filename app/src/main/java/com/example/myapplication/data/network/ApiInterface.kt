package com.example.myapplication.data.network

import com.example.myapplication.data.FlickResponseEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiInterface {
    @GET("/services/rest/")
    fun fetchFlickItems(@QueryMap queryMap : HashMap<String, String>) : Single<FlickResponseEntity>
    @GET
    fun getKamaterahoItems(@Url url : String) : Single<FlickResponseEntity>
}