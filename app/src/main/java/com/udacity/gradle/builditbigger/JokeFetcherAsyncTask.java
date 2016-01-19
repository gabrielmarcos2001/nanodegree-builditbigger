package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.example.gabrielmarcos.joker.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by gabrielmarcos on 1/19/16.
 *
 * This AsyncTask is responsible for getting a Joke from the GCE Server
 *
 * It uses the callback pattern so it can communicate with both the MainActivity
 * and the Tests
 */
public class JokeFetcherAsyncTask extends AsyncTask<String, Void, String> {

    public interface TaskCallback {
        void onComplete(String joke);
    }

    private TaskCallback mCallback;
    private MyApi myApiService = null;

    @Override
    protected String doInBackground(String... params) {
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
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mCallback != null) {
            mCallback.onComplete(result);
        }
    }

    public void setmCallback(TaskCallback mCallback) {
        this.mCallback = mCallback;
    }
}
