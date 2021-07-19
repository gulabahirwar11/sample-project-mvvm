package com.example.myapplication.data.reposiotry.flickr

import com.example.myapplication.data.reposiotry.flickr.mapper.ItemMapper
import com.example.myapplication.data.network.ApiInterface
import com.example.myapplication.domain.datasouce.FlickrDataSource
import com.example.myapplication.domain.entity.FlickrData
import io.reactivex.Single
import java.util.HashMap

private val METHOD = "method"
private val API_KEY = "api_key"
private val TEXT = "text"
private val FORMAT = "format"
private val NO_JSON_CALLBACK = "nojsoncallback"
private val PER_PAGE = "per_page"
private val PAGE = "page"
private val EXTRA = "extra"

private var KAMATERAHO_API_URL = "https://kamateraho.s3.ap-south-1.amazonaws.com/test_response.json"

class FlickrRepository(private val apiInterface: ApiInterface) : FlickrDataSource {
    override fun getFetchFlickrItems(page: Int): Single<FlickrData> {
        return apiInterface.fetchFlickItems(getQueryMap(page)).map { ItemMapper.transform(it) }
    }

    override fun getKamaterahoItems(): Single<FlickrData> {
        return apiInterface.getKamaterahoItems(KAMATERAHO_API_URL).map { ItemMapper.transform(it) }
    }

    private fun getQueryMap(page: Int): HashMap<String, String> {
        val queryMap = LinkedHashMap<String, String>()
        queryMap[METHOD] = "flickr.photos.search"
        queryMap[API_KEY] = "641c87bd78e54920b01e9a5d8bb726d7"
        queryMap[TEXT] = "polo"
        queryMap[FORMAT] = "json"
        queryMap[NO_JSON_CALLBACK] = "1"
        queryMap[PER_PAGE] = "20"
        queryMap[EXTRA] = "url_q"
        queryMap[PAGE] = page.toString()
        return queryMap
    }
}
