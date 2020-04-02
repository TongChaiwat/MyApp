package com.charmist.myapp

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.longClick
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginTest {


    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    private lateinit var name: String

    @Before
    fun setup() {
        name = "TongNaja"
    }

    @Test
    fun userTapLoginButton_shouldBe_SecondPage() {
        // MainActivity.
        activityRule.launchActivity(null)

        //click enter name
        onView(withId(R.id.etName)).perform(
            ViewActions.typeText(name),
            ViewActions.closeSoftKeyboard()
        )

        //click login
        onView(withId(R.id.btnLogin))
            .perform(click())


        //check name label
        onView(
            Matchers.allOf(
                isDescendantOfA(withId(R.id.btnLogin)),
                isDisplayed()
            )
        )

        // SecondActivity.


        //check name label
        onView(withId(R.id.tvHello))
            .check(matches(isDisplayed()))
            .check(matches(withText("Hello $name")))

    }
    @Test
    fun userLongClickLoginButton_shouldBe_SecondPage() {
        // MainActivity.
        activityRule.launchActivity(null)
        //long click login
        onView(withId(R.id.btnLogin))
            .perform(longClick())


        // SecondActivity.
        //check name label
        onView(withId(R.id.tvHello))
            .check(matches(isDisplayed()))
            .check(matches(withText("Hello Hello")))

        onView(withId(R.id.tvHello))
            .perform()

    }
}
