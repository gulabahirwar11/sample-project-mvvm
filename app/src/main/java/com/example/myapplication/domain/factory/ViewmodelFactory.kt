package com.example.myapplication.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.datasouce.FlickrDataSource
import com.example.myapplication.flickr.feature.flickr.viewmodel.FlickrViewmodel

class ViewmodelFactory (private val flickrDataSource: FlickrDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FlickrViewmodel::class.java)) {
            return FlickrViewmodel(flickrDataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}