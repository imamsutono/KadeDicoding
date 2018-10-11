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

    // test for swipe between tab is work correctly
    @Test
    fun swipePage() {
        onView(withId(viewpager_main)).check(matches(isDisplayed()))
        onView(withId(viewpager_main)).perform(swipeLeft())
    }

    // check for tab layout is displayed
    @Test
    fun checkTabLayoutDisplayed() {
        onView(withId(tabs_main))
                .perform(click())
                .check(matches(isDisplayed()))
    }

    @Test
    fun testAppBehaviour() {
        // delay code execution
        sleep(DELAY)
        // check recycler view is displayed
        onView(withId(prev_match_list))
                .check(matches(isDisplayed()))

        sleep(DELAY)
        // scroll recycler view to item in position 5
        onView(withId(prev_match_list)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5)
        )
        // click item in position 5 in recycler view, and go to detail activity
        onView(withId(prev_match_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click())
        )

        sleep(DELAY)
        // check home team badge image view is displayed
        onView(withId(home_badge))
                .check(matches(isDisplayed()))
        // check away team badge image view is displayed
        onView(withId(away_badge))
                .check(matches(isDisplayed()))

        // check add to favorite (star) menu is displayed
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        // click add to favorite menu to add item to sqlite database
        onView(withId(add_to_favorite)).perform(click())

        sleep(DELAY)
        // back from detail activity to main activity
        pressBack()

        sleep(DELAY)
        // click view that contain FAVORITE text, in this case is favorite tab
        onView(withText("FAVORITE")).perform(click())

        sleep(DELAY)
        // scroll to first item in favorite recycler view
        onView(withId(favorites_match_list)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
        // click first item in favorite recycler view. item is added in past test
        onView(withId(favorites_match_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        sleep(DELAY)
        // click add to favorite menu to removed item from sqlite database
        onView(withId(add_to_favorite)).perform(click())

        pressBack()

        sleep(DELAY)
        // swipe down favorite tab to refresh recycler view and check that item is removed
        onView(withId(favorite_swipe_refresh)).perform(swipeDown())

        sleep(DELAY)
    }
}