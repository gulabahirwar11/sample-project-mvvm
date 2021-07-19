package com.example.myapplication.flickr.feature.flickr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class FlickrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flickr_activity)

        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, FlickrPhotoFragment(), FlickrPhotoFragment::class.java.name)
                    .disallowAddToBackStack()
                    .commit();
    }
}