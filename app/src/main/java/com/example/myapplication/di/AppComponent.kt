package com.example.myapplication.di

import com.example.myapplication.flickr.feature.flickr.FlickrPhotoFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: FlickrPhotoFragment)
}