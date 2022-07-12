package com.example.demokotlinflow.presentation.addon.view.fragment

import android.R.attr.theme
import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.Companion.launchInContainer
import androidx.fragment.app.testing.FragmentScenario.FragmentAction
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.demokotlinflow.presentation.addon.adapter.AddOnAdapter
import com.example.demokotlinflow.presentation.addon.view.activity.AddOnActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class AddOnListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AddOnActivity::class.java)

//    val LIST_ITEM_IN_TEST = 4
//    val ADDON_IN_TEST = FakeAddOnData.addonList[LIST_ITEM_IN_TEST - 1]
//
//
    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(com.example.demokotlinflow.R.id.rcvAddOn)).check(matches(isDisplayed()))
    }
//
//
//    @Test
//    fun test_rcv_item_click() {
//        onView(withId(com.example.demokotlinflow.R.id.rcvAddOn)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<AddOnAdapter.AddOnViewHolder>(
//                0,
//                MyViewAction().clickChildViewWithId(com.example.demokotlinflow.R.id.txtName)
//            )
//        )
//    }
//
//    @Test
//    fun test_fragment_navigation() {
//        val args = Bundle()
//        val navhostScenario: FragmentScenario<AddOnListFragment> =
//            launchInContainer(AddOnListFragment::class.java, args, theme, Lifecycle.State.STARTED)
//
//        navhostScenario.onFragment { fragment: AddOnListFragment ->
//
//            assertNotNull(fragment.activity)
//            val navController = TestNavHostController(fragment.activity!!)
//            fragment.activity!!.runOnUiThread { navController.setGraph(com.example.demokotlinflow.R.navigation.add_on_nav_graph) }
//            Navigation.setViewNavController(fragment.requireView(), navController)
//
//            onView(withId(com.example.demokotlinflow.R.id.rcvAddOn)).perform(
//                RecyclerViewActions.actionOnItemAtPosition<AddOnAdapter.AddOnViewHolder>(
//                    0,
//                    MyViewAction().clickChildViewWithId(com.example.demokotlinflow.R.id.txtName)
//                )
//            )
//
//            // Then navigate
//            navController.navigate(com.example.demokotlinflow.R.id.action_addOnListFragment_to_addOnDetailFragment)
//            val destination = navController.currentDestination
//            assertNotNull(destination)
//            assertEquals(destination!!.id, com.example.demokotlinflow.R.id.addOnDetailFragment)
//        }
//    }

}