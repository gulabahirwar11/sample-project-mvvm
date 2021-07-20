package com.example.myapplication.flickr

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.myapplication.R
import com.example.myapplication.flickr.feature.flickr.UpstoxActivity
import com.example.myapplication.flickr.feature.flickr.adapter.UpstoxRecyclerAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class UpstoxFragmentTest {
    @Rule
    @JvmField
    var flickrPhotoActivityActivityTestRule = ActivityTestRule(UpstoxActivity::class.java)

    @Test
    fun testRecyclerView() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testRecyclerScroll() {
           val recyclerView = flickrPhotoActivityActivityTestRule
                   .activity.findViewById<RecyclerView>(R.id.recycler_view)
           val itemCount = recyclerView.adapter?.itemCount?:0

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition<UpstoxRecyclerAdapter.FlickrViewHolder>(itemCount - 1))
    }
}