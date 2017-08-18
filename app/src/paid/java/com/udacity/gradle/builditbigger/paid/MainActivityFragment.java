package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.R;

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

        joke_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });

        progressBar.setVisibility(View.GONE);
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
