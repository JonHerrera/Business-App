package com.example.jonlh.rbcsmallbusinessmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class start_business_offers extends AppCompatActivity {

    private TextView offerTextView;
    private TextView offerTextView2;
    private int industryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_business_offers);

        //the following grabs the industry option that the user has selected from the intent of the last activity
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            industryName = extras.getInt("industryName");
        }

        offerTextView = (TextView) findViewById(R.id.offerTextView);
        offerTextView.setText(getString(R.string.start_business_offers));

        offerTextView2 = (TextView) findViewById(R.id.offerTextView2);
        offerTextView2.setMovementMethod(new ScrollingMovementMethod());

        //TODO: DATABASE - have this connect to DB array and correspond to the users selection from last activity
        switch(industryName){ //using the selection from the previous activity, we can set the appropriate information
            case 0: offerTextView2.setText("No industry has been selected");
                break;
            case 1: offerTextView2.setText(getResources().getString(R.string.industry_Agriculture_details));
                break;
            case 2: offerTextView2.setText(getResources().getString(R.string.industry_IT_details));
                break;
            case 3: offerTextView2.setText(getResources().getString(R.string.industry_Retail_details));
                break;
            default:
        }

    }
}
