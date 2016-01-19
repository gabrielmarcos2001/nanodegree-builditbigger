package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.util.Pair;

import com.example.gabrielmarcos.joker.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by gabrielmarcos on 1/19/16.
 */
public class MainPresenter {

    private Context mContext;
    private boolean mLoading = false;
    private MainView mView;

    /**
     * Constructor
     * @param context
     */
    public MainPresenter(Context context) {
        this.mContext = context;
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
                new EndpointsAsyncTask().execute(new Pair<Context, String>(mContext, null));
            }
        }, 200);

    }

    /**
     * Async Task used for getting Jokes from the Server
     */
    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

        private MyApi myApiService = null;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {

            if(myApiService == null) {

                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://totemic-fact-119420.appspot.com/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                myApiService = builder.build();
            }

            try {
                return myApiService.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            mLoading = false;

            if (mView != null) {
                mView.hideLoader();
                mView.displayJoke(result);
            }

        }
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setmView(MainView mView) {
        this.mView = mView;
    }
}
