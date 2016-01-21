package com.udacity.gradle.builditbigger;

import android.os.Handler;

/**
 * Created by gabrielmarcos on 1/19/16.
 */
public class MainPresenter {

    private boolean mLoading = false;
    private MainView mView;

    /**
     * Constructor
     */
    public MainPresenter( ) {

    }

    /**
     * Starts the tasks for getting a Joke
     */
    public void getJokes() {

        if (mLoading) return;

        mLoading = true;

        if (mView != null) {
            mView.showLoader();
        }

        // We Start getting the joke after a small delay, so we are sure the
        // loader animation is finished
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                JokeFetcherAsyncTask task = new JokeFetcherAsyncTask();
                task.setmCallback(new JokeFetcherAsyncTask.TaskCallback() {
                    @Override
                    public void onComplete(String joke) {
                        mLoading = false;

                        if (mView != null) {
                            mView.displayJoke(joke);
                        }
                    }
                });
                task.execute();
            }
        }, 500);

    }

    public void setmView(MainView mView) {
        this.mView = mView;
    }
}
