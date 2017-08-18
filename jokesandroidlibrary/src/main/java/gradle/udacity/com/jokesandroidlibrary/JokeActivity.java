package gradle.udacity.com.jokesandroidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    TextView jokes_text;
    String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        jokes_text = (TextView) findViewById(R.id.joke_text);
        Intent intent = getIntent();
        joke = intent.getStringExtra(getString(R.string.joke_intent_extra));

        if (joke != null) {
            jokes_text.setText(joke);
        } else {
            jokes_text.setText(getString(R.string.joke_error));
        }
    }
}
