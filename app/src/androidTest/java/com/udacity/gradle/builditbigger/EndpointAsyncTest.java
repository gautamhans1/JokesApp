package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.MainActivityFragment;

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
