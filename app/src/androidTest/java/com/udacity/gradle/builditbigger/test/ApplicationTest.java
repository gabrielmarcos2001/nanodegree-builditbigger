package com.udacity.gradle.builditbigger.test;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.udacity.gradle.builditbigger.JokeFetcherAsyncTask;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

}