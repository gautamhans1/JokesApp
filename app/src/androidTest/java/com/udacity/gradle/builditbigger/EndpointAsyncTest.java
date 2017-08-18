package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertTrue;

/**
 * Created by Gautam on 18-Aug-17.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointAsyncTest {
    @Test
    public void testDoInBackground() throws Exception {
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        mainActivityFragment.flag = true;
        new EndpointAsyncTask().execute(mainActivityFragment);
        Thread.sleep(5000);
        assertTrue("Error: Joke = " + mainActivityFragment.joke, mainActivityFragment.joke != null);
    }

}
