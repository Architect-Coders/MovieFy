package com.moviefy

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.moviefy.ui.detailMovie.DetailMovieActivity
import com.moviefy.utils.mockedMovie
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailActivityTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<DetailMovieActivity>(DetailMovieActivity::class.java, true, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val intent = Intent()
        intent.putExtra(DetailMovieActivity.MOVIE, mockedMovie)
        activityRule.launchActivity(intent)
    }

    @Test
    fun check_intent_is_received_and_show_elements(){
        Espresso.onView(ViewMatchers.withId(R.id.movieDescription))
            .check(ViewAssertions.matches(ViewMatchers.withText("Overview")))
    }
}