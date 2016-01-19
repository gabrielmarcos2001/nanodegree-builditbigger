package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by gabrielmarcos on 1/19/16.
 *
 * Test Case used for verifying the AsyncTask returns any Joke
 * an not a null object
 *
 */
public class JokeAsyncTaskTest extends AndroidTestCase {

    private String mJoke;
    private CountDownLatch mSignal;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mSignal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mSignal.countDown();
    }

    public void testAsyncTask() throws Throwable {

        JokeFetcherAsyncTask asyncTask = new JokeFetcherAsyncTask();
        asyncTask.setmCallback(new JokeFetcherAsyncTask.TaskCallback() {
            @Override
            public void onComplete(String joke) {

                // Assigns the joke and starts the countdown to unlock the thread
                mJoke = joke;
                mSignal.countDown();
            }
        });

        // Executes the task
        asyncTask.execute();

        // Blocks the thread until the async task finishes
        mSignal.await();

        // Checks that the Async Task does not return a null object as a Joke
        assertNotNull(mJoke);
    }
}
