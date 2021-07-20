package com.example.myapplication.flickr.feature.flickr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class UpstoxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upstox_activity)

        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, UpstoxFragment(), UpstoxFragment::class.java.name)
                    .disallowAddToBackStack()
                    .commit();
    }
}