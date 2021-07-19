package com.example.myapplication.domain.datasouce

import com.example.myapplication.domain.entity.Flickr
import com.example.myapplication.domain.entity.FlickrData
import io.reactivex.Single
import java.util.ArrayList

interface FlickrDataSource {
    fun getFetchFlickrItems(page : Int) : Single<FlickrData>
    fun getKamaterahoItems() : Single<FlickrData>
}