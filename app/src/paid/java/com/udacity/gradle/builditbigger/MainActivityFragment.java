package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.gabrielmarcos.joker.backend.myApi.MyApi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View mLoader;
    private View mButton;

    public MainActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mLoader = root.findViewById(R.id.loader);
        mButton = root.findViewById(R.id.joke_face);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        mButton.setVisibility(View.VISIBLE);
        mLoader.setVisibility(View.INVISIBLE);
    }

    /**
     * Displays an Awesome loader with an awesome animation
     */
    public void showLoader() {

        if (mLoader.getVisibility() == View.VISIBLE) return;

        Animation showAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        mLoader.setVisibility(View.VISIBLE);
        mLoader.startAnimation(showAnim);

        Animation hideAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
        mButton.startAnimation(hideAnim);
        hideAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isAdded()) {
                    mButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * Hides The Awesome Loader with an awesome animation
     */
    public void hideLoader() {

        if (mButton.getVisibility() == View.VISIBLE) return;

        Animation showAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        mButton.setVisibility(View.VISIBLE);
        mButton.startAnimation(showAnim);

        mLoader.setVisibility(View.INVISIBLE);

    }

}
