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
import com.imamsutono.footballmatchschedule.home.HomeActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    private val DELAY: Long = 2000

    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.imamsutono.footballmatchschedule", appContext.packageName)
    }

    @Test
    fun testAppBehaviour() {
        sleep(DELAY)
        onView(withId(viewpager_home)).check(matches(isDisplayed()))
        onView(withId(viewpager_home)).perform(swipeLeft())

        sleep(DELAY)
        onView(withId(rv_prev_match)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        // click item in position 5 in recycler view, and go to team detail activity
        onView(withId(rv_prev_match)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

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
        pressBack()

        onView(withId(tab_item_teams)).perform(click())
        onView(withId(rv_team)).check(matches(isDisplayed()))

        // fragment team
        sleep(DELAY)
        // check recyclerview is has data
        onView(withText("Arsenal")).check(matches(isDisplayed()))

        sleep(DELAY)
        onView(withId(spinner_league)).perform(click())
        onView(withText("Spanish La Liga")).perform(click())

        sleep(DELAY)
        onView(withText("Barcelona")).check(matches(isDisplayed()))

        onView(withId(rv_team)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5)
        )
        // click item in position 5 in recycler view, and go to team detail activity
        onView(withId(rv_team)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click())
        )

        // team detail activity
        sleep(DELAY)
        onView(withId(viewpager_team)).perform(swipeLeft())

        sleep(DELAY)
        onView(withId(rv_player)).check(matches(isDisplayed()))
        onView(withId(rv_player)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5)
        )
        // click item in position 5 in recycler view, and go to team detail activity
        onView(withId(rv_player)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click())
        )

        // team player detail activity
        onView(withId(img_player_fanart))
                .check(matches(isDisplayed()))
        sleep(DELAY)
        pressBack()

        // team detail activity
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        sleep(DELAY)
        pressBack()
        onView(withId(tab_item_favorites)).perform(click())

        // favorite fragment
        sleep(DELAY)
        onView(withId(favorites_match_list))
                .check(matches(isDisplayed()))
        onView(withId(favorites_match_list)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        // match detail activity
        sleep(DELAY)
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        sleep(DELAY)
        pressBack()
        // favorite fragment
        onView(withId(swipe_favorite_matchs)).perform(swipeDown())
        onView(withId(viewpager_favorite)).perform(swipeLeft())

        sleep(DELAY)
        onView(withId(rv_favorite_teams)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        sleep(DELAY)
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        sleep(DELAY)
        pressBack()
        onView(withId(swipe_favorite_teams)).perform(swipeDown())
    }
}