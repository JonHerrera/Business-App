package com.example.jonlh.rbcsmallbusinessmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //calling the components from the view into objects
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_business_welcome); //this is the greeting screen

        //TODO: the first screen should be a login screen as opposed to this
        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        initializeActivityText(); //sets the initial text for the textview
    }

    //setting the TextView on the XML to the String here
    private void initializeActivityText() {
        welcomeTextView.setText(R.string.start_business_welcome);
    }

    //moving onto the next screen
    //TODO: change the view without changing the activity, if possible (probably not)
    public void continueClick(View view) {
        Intent i = new Intent(this, start_business_industry.class);
        startActivity(i);
    }
}
