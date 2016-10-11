package uk.co.jackdh.funfacts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.co.jackdh.funfacts.API.FactAPI;
import uk.co.jackdh.funfacts.model.Fact;

public class FunFactsActivity extends AppCompatActivity {
    private static final String TAG = FunFactsActivity.class.getSimpleName();
    // Declare TextView
    private TextView mFactTextView;
    private Button mShowFactButton;
    private RelativeLayout mRelativeLayout;
    private FactBook mFactBook = new FactBook();
    private Color mColor;
    private Random mRandom;
    private RandomFact mRandomFact;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        // Assign views from layout file to corresponding variables
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_fun_facts);
        mFactTextView = (TextView) findViewById(R.id.factTextView);
        mShowFactButton = (Button) findViewById(R.id.showFactButton);
        mProgressBar = (ProgressBar) findViewById(R.id.loadingBar);
        mColor = new Color();
        mRandom = new Random();
        test();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        };

        mShowFactButton.setOnClickListener(listener);

//        Toast.makeText(FunFactsActivity.this, "Activity created.", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "We're logging from on create method");
    }

    public void test() {
        // Update Screen with dynamic fact
        int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
        mRelativeLayout.setBackgroundColor(color);
        mShowFactButton.setTextColor(color);
        mProgressBar.setVisibility(View.VISIBLE);
        getFact(new Callback<Fact>() {
            @Override
            public void success(Fact fact, Response response) {
                Log.d(TAG, "Fact is: " + fact.getValue());
                mFactTextView.setText(fact.getValue());
                mProgressBar.setVisibility(View.INVISIBLE);
            }
            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Fact is: failed: " + error.getMessage());
            }
        });
    }

    public void getFact(Callback<Fact> factCallback) {
        Log.d(TAG, "Fact is: HIT THE BUTTON");
        String api = "https://api.chucknorris.io";
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(api).build();
        FactAPI factAPI = restAdapter.create(FactAPI.class);

        factAPI.getFeed(factCallback);

    }
}























