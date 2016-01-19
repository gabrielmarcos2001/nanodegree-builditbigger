package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gabilam.com.jokedisplay.JokeActivity;

public class MainActivity extends AppCompatActivity implements MainView {

    private static MainPresenter mPresenter;

    private MainActivityFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mPresenter == null) {
            mPresenter = new MainPresenter(this);
        }else {
            mPresenter.setmContext(this);
        }

        mFragment = (MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
    }

    /**
     *
     * This method is called from the Main button on the app
     * for getting a new Joke
     *
     * @param view
     */
    public void tellJoke(View view){
        mPresenter.getJokes();
    }

    public void showLoader() {
        mFragment.showLoader();
    }

    public void hideLoader() {
        mFragment.hideLoader();
    }

    @Override
    public void displayJoke(String joke) {
        Intent i = new Intent(this, JokeActivity.class);
        i.putExtra(JokeActivity.JOKE_KEY,joke);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setmView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.setmView(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isFinishing()) {
            mPresenter = null;
        }
    }
}
