package com.example.myapplication.flickr

import androidx.fragment.app.FragmentTransaction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.myapplication.R
import com.example.myapplication.flickr.feature.flickr.FlickrActivity
import com.example.myapplication.flickr.feature.flickr.FlickrPhotoFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class FlickrActivityTest {

    @Rule
    @JvmField
    var flickrPhotoActivityActivityTestRule = ActivityTestRule(FlickrActivity::class.java)
    var flickrActivity : FlickrActivity?= null

    @Before
    fun setUp()  {
       flickrActivity = flickrPhotoActivityActivityTestRule.activity
    }

    @Test
    fun test_fragmentLaunch() {
        flickrActivity?.runOnUiThread {
            startFragment()
        }
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
    }

    private fun startFragment(): FlickrPhotoFragment? {
        val transaction: FragmentTransaction = flickrActivity?.supportFragmentManager?.beginTransaction()?:return null
        val flickrPhotoFragment = FlickrPhotoFragment()
        transaction.add(flickrPhotoFragment, FlickrPhotoFragment::class.java.name)
        transaction.commit()
        return flickrPhotoFragment
    }

   @After
   fun tearDown() {
       flickrActivity = null
   }
}