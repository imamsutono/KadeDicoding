package com.imamsutono.footballmatchschedule

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.imamsutono.footballmatchschedule.R.id.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val DELAY: Long = 2000

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.imamsutono.footballmatchschedule", appContext.packageName)
    }

    @Test
    fun swipePage() {
        onView(withId(viewpager_main)).check(matches(isDisplayed()))
        onView(withId(viewpager_main)).perform(swipeLeft())
    }

    @Test
    fun checkTabLayoutDisplayed() {
        onView(withId(tabs_main))
                .perform(click())
                .check(matches(isDisplayed()))
    }

    @Test
    fun testAppBehaviour() {
        sleep(DELAY)
        // check recycler view is displayed
        onView(withId(prev_match_list))
                .check(matches(isDisplayed()))

        sleep(DELAY)
        // scroll recycler view to item in position 5
        onView(withId(prev_match_list)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5)
        )
        // click item in position 5 in recycler view
        onView(withId(prev_match_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click())
        )

        sleep(DELAY)
        onView(withId(home_badge))
                .check(matches(isDisplayed()))
        onView(withId(away_badge))
                .check(matches(isDisplayed()))

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        sleep(DELAY)
        pressBack()

        sleep(DELAY)
        onView(withText("FAVORITE")).perform(click())

        sleep(DELAY)
        onView(withId(favorites_match_list)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
        onView(withId(favorites_match_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        sleep(DELAY)
        onView(withId(add_to_favorite)).perform(click())

        pressBack()

        sleep(DELAY)
        onView(withId(favorite_swipe_refresh)).perform(swipeDown())

        sleep(DELAY)
    }
}