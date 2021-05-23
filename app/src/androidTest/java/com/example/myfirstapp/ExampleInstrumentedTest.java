package com.example.myfirstapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myfirstapp", appContext.getPackageName());
    }


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class)
            ;

// Test The title of the page on main activity
    @Test
    public void testTitle() {
        onView(withId(R.id.title)).check(matches(withText("My Tasks")));
    }

// Test the image
    @Test
    public void testImage() {
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
    }

// Test Recycler View
    @Test
    public void testRecyclerView() {
        onView(withId(R.id.recyclerView)).perform(click());
    }

// Test RecyclerVoew Item
    @Test
    public void testTasksTitle() {
        onView(withId(R.id.taskTitle)).perform(click());

    }


    @Rule
    public ActivityScenarioRule<Details> activityRule2 =
            new ActivityScenarioRule<>(Details.class)
            ;
    @Test
    public void testTitle2() {
        onView(withId(R.id.textView6));
    }

    @Test
    public void testButton() {
        onView(withId(R.id.button)).perform(click());

    }

    @Test
    public void testButtonText() {
        onView(withId(R.id.button)).check(matches(withText("Add Task")));
    }

    @Rule
    public ActivityScenarioRule<Settings> activityRule3 =
            new ActivityScenarioRule<>(Settings.class)
            ;
    @Test
    public void testTitle3() {
        onView(withId(R.id.textView9));
    }

    @Test
    public void testTitleOfSettings() {
        onView(withId(R.id.editTextTextPersonName3));
    }

}