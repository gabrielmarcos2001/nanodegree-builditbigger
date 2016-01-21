package com.udacity.gradle.builditbigger.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import com.udacity.gradle.builditbigger.JokeFetcherAsyncTask;
import com.udacity.gradle.builditbigger.MainActivity;

import org.junit.Before;

import java.util.concurrent.CountDownLatch;

/**
 * Created by gabrielmarcos on 1/21/16.
 */
public class TestMainActivity extends ActivityInstrumentationTestCase2<MainActivity> {

    private String mJoke;
    private CountDownLatch mSignal;

    public TestMainActivity() {
        super(MainActivity.class);

    }

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        mSignal = new CountDownLatch(1);
    }

    @MediumTest
    public void testTask() throws Exception{

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
