package com.example.demokotlinflow.presentation.login.view.activity

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.demokotlinflow.R
import com.example.demokotlinflow.presentation.addon.view.activity.AddOnActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest{

    @get:Rule
    val intentsTestRule = IntentsTestRule(LoginActivity::class.java)


    @Test
    fun check_valid_input_fields(){
        Espresso.onView(withId(R.id.tietMobile)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tietPassword)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tietMobile)).perform(ViewActions.typeText("9639639631"))
        Espresso.onView(withId(R.id.tietPassword)).perform(ViewActions.typeText("Demo@123"))
        closeSoftKeyboard()

        Espresso.onView(withId(R.id.fabLogin)).perform(ViewActions.click())
        intended(hasComponent(AddOnActivity::class.java.name))

    }



//    @Test
//    fun check_invalid_input_fields(){
//        Espresso.onView(withId(R.id.tietMobile)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        Espresso.onView(withId(R.id.tietPassword)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//
//        Espresso.onView(withId(R.id.tietMobile)).perform(ViewActions.typeText("9639639631"))
//        Espresso.onView(withId(R.id.tietPassword)).perform(ViewActions.typeText("Demo@123"))
//        closeSoftKeyboard()
//        Espresso.onView(withId(R.id.fabLogin)).perform(ViewActions.click())
//
//        assert(false
//        ) {
//            Espresso.onView(withId(R.id.tietMobile))
//                .check(ViewAssertions.matches(withText("963963963")))
//        }
//
//        assert(false
//        ) {
//            Espresso.onView(withId(R.id.tietPassword)).check(ViewAssertions.matches(withText("Demo@12")))
//        }
//    }
}