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

/**
 * Created by Gautam on 18-Aug-17.
 */

public class MainActivity extends AppCompatActivity {

    private Jokes mJokes;
    private Context mContext;
    private MainActivityFragment mMainActivityFragment;
    private String mResult = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mJokes = new Jokes();

        FragmentManager fm = getSupportFragmentManager();
        mMainActivityFragment = (MainActivityFragment) fm.findFragmentById(R.id.fragment_main);
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
        mMainActivityFragment.showProgress();
        new EndpointsAsyncTask(this).execute(this);

    }

    public void hideProgress() {
        mMainActivityFragment.hideProgress();
    }

    public String getJoke() {
        return new Jokes().tellJoke();
    }

}
