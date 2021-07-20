package com.example.myapplication.data.reposiotry.flickr

import com.example.myapplication.data.reposiotry.flickr.mapper.ItemMapper
import com.example.myapplication.data.network.ApiInterface
import com.example.myapplication.domain.datasouce.UpstoxDataSource
import com.example.myapplication.domain.entity.Upstox
import io.reactivex.Single

private var UPSTOX_API_URL = "https://run.mocky.io/v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf"

class FlickrRepository(private val apiInterface: ApiInterface) : UpstoxDataSource {
    override fun getUpstoxItems(): Single<List<Upstox>> {
        return apiInterface.getUpstoxItems(UPSTOX_API_URL).map { ItemMapper.transform(it) }
    }
}
