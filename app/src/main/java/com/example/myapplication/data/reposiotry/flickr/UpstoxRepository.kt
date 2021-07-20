package com.example.myapplication.data.reposiotry.flickr

import com.example.myapplication.data.reposiotry.flickr.mapper.ItemMapper
import com.example.myapplication.data.network.ApiInterface
import com.example.myapplication.domain.datasouce.UpstoxDataSource
import com.example.myapplication.domain.entity.Upstox
import io.reactivex.Single


class FlickrRepository(private val apiInterface: ApiInterface) : UpstoxDataSource {
    override fun getUpstoxItems(): Single<List<Upstox>> {
        return apiInterface.getUpstoxItems().map { ItemMapper.transform(it) }
    }
}
