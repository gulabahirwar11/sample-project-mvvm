package com.example.myapplication.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.datasouce.UpstoxDataSource
import com.example.myapplication.flickr.feature.flickr.viewmodel.UpstoxViewmodel

class ViewmodelFactory (private val upstoxDataSource: UpstoxDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpstoxViewmodel::class.java)) {
            return UpstoxViewmodel(upstoxDataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}