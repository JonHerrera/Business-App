package com.example.jonlh.rbcsmallbusinessmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class start_business_industry extends MainActivity
        implements OnItemSelectedListener {
            //the implementation is needed for the Adapter on the Spinner when an item is selected

    private TextView industryTextView;
    private ListView industryDetailsListView;
    private Button continueButton2;
    public int industryName;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        //a method created for when items are selected
        //this method will get the position in the array of the item selected to be used
        industryName = (int) parent.getItemIdAtPosition(pos); //casting the position that was retrieved as a long into an int
        switch (industryName) { //based on what position the item is in the array at, we can call the corresponding data from another array
            //TODO: DATABASE - set it so that on Spinner Item Click, get the corresponding information and display it in the ListView
            case 0:
                industryDetailsListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.industry_null)));
                break;
            case 1:
                industryDetailsListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.industry_Agriculture)));
                break;
            case 2:
                industryDetailsListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.industry_Information_Technology)));
                break;
            case 3:
                industryDetailsListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.industry_Retail)));
                break;
            default:
        }
        //so the user cannot proceed to the next page without selecting an industry
        if(industryName == 0) { continueButton2.setEnabled(false); }
        else { continueButton2.setEnabled(true); }
    }
    public void onNothingSelected(AdapterView<?> parent) {    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_business_industry);

        //calling the components from the view to be used
        industryTextView = (TextView) findViewById(R.id.industryTextView);
        industryDetailsListView = (ListView) findViewById(R.id.industryDetailsListView);
        Spinner industrySpinner = (Spinner) findViewById(R.id.industrySpinner);
        continueButton2 = (Button) findViewById(R.id.continueButton);

        //TODO: DATABASE - the selection array should be called from the database as opposed to a hardcoded one (possibly use the primary keys)
        ArrayAdapter<CharSequence> spinnerAdapter = //creating the array adapter for the spinner
                ArrayAdapter.createFromResource(this, R.array.industry_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //spinner selection display
        industrySpinner.setAdapter(spinnerAdapter); //connecting spinner to adapter

        industrySpinner.setOnItemSelectedListener(this); //attaching the item selected method to the Spinner
        initializeText(); //filling in the TextViews
    }

    private void initializeText() {
        industryTextView.setText(R.string.what_industry);
    }

    public void continueClick(View view) {
        Intent i = new Intent(this, start_business_offers.class);
        i.putExtra("industryName", industryName); //upon clicking the continue button, the industry you have selected
            //will be passed to the next activity in the form of an int, to be used to call more data
        startActivity(i);
    }
}