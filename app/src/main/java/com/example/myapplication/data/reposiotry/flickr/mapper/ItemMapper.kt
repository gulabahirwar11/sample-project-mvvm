package com.example.myapplication.data.reposiotry.flickr.mapper

import com.example.myapplication.data.FlickResponseEntity
import com.example.myapplication.domain.entity.Flickr
import com.example.myapplication.domain.entity.FlickrData
import java.util.*

object ItemMapper {
    fun transform(response: FlickResponseEntity): FlickrData {
        val flickrList = ArrayList<Flickr>()

        for (entity in response.flickrDataEntity.flickEntityList) {
            flickrList.add(Flickr(entity.id,
                    entity.onwer,
                    entity.serect,
                    entity.server,
                    entity.farm,
                    entity.title))
        }
        return FlickrData(response.flickrDataEntity.pages, flickrList)
    }
}