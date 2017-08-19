package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.Jokes;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Gautam on 18-Aug-17.
 */

public class MainActivity extends AppCompatActivity {

    private Jokes mJokes;
    private InterstitialAd mInterstitialAd;
    private Context mContext;
    private MainActivityFragment mMainActivityFragment;
    private String mResult = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mContext = this;
        mJokes = new Jokes();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        requestNewInterstitial();

        FragmentManager fm = getSupportFragmentManager();
        mMainActivityFragment = (MainActivityFragment) fm.findFragmentById(R.id.fragment_main);

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            mMainActivityFragment.showProgress();
            new EndpointsAsyncTask(mContext).execute(this);
        }

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                mMainActivityFragment.showProgress();
                new EndpointsAsyncTask(mContext).execute(mContext);
            }
        });
    }

    public void execTask() {
        new EndpointsAsyncTask(mContext).execute();
    }

    public void hideProgress() {
        mMainActivityFragment.hideProgress();
    }

    public String getJoke() {
        return new Jokes().tellJoke();
    }

    public Context getContext() {
        return mContext;
    }
}
