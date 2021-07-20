package com.example.myapplication.di

import com.example.myapplication.data.network.ApiFactory
import com.example.myapplication.data.reposiotry.flickr.FlickrRepository
import com.example.myapplication.domain.datasouce.UpstoxDataSource
import dagger.Module
import dagger.Provides

@Module
class FlickrRepositoryModule {
    @Provides
    fun provideFlickrRepository(): UpstoxDataSource {
        return FlickrRepository(ApiFactory.getInstance())
    }
}