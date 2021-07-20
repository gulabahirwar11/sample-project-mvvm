package com.example.myapplication.flickr

import androidx.fragment.app.FragmentTransaction
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.myapplication.R
import com.example.myapplication.flickr.feature.flickr.UpstoxActivity
import com.example.myapplication.flickr.feature.flickr.UpstoxFragment
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class UpstoxActivityTest {

    @Rule
    @JvmField
    var flickrPhotoActivityActivityTestRule = ActivityTestRule(UpstoxActivity::class.java)
    var upstoxActivity : UpstoxActivity?= null

    @Before
    fun setUp()  {
       upstoxActivity = flickrPhotoActivityActivityTestRule.activity
    }

    @Test
    fun test_fragmentLaunch() {
        upstoxActivity?.runOnUiThread {
            startFragment()
        }
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()))
    }

    private fun startFragment(): UpstoxFragment? {
        val transaction: FragmentTransaction = upstoxActivity?.supportFragmentManager?.beginTransaction()?:return null
        val flickrPhotoFragment = UpstoxFragment()
        transaction.add(flickrPhotoFragment, UpstoxFragment::class.java.name)
        transaction.commit()
        return flickrPhotoFragment
    }

   @After
   fun tearDown() {
       upstoxActivity = null
   }
}