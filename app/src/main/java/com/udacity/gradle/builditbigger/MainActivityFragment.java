package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import gradle.udacity.com.jokesandroidlibrary.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public boolean flag = false;

    public String joke;
    @BindView(R.id.joke_pb)
    ProgressBar progressBar;

    @BindView(R.id.joke_btn)
    Button joke_btn;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);


        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        joke_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });

        progressBar.setVisibility(View.GONE);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public void getJoke() {
        new EndpointAsyncTask().execute(this);
    }

    public void showJokeActivity() {
        if (!flag) {
            Context context = getActivity();
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(context.getString(R.string.joke_intent_extra), joke);
            context.startActivity(intent);
            progressBar.setVisibility(View.GONE);

        }
    }
}
