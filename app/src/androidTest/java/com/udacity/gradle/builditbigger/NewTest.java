package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Gautam on 19-Aug-17.
 */

@RunWith(AndroidJUnit4.class)
public class NewTest {

    String res = null;

    @Test
    public void testAsync() throws InterruptedException, ExecutionException, TimeoutException{
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(InstrumentationRegistry.getTargetContext());
        endpointsAsyncTask.execute(InstrumentationRegistry.getTargetContext());
        res = endpointsAsyncTask.get(5, TimeUnit.SECONDS);
        Log.d("Joke: ", res);
        assertNotNull(res);
    }

}
