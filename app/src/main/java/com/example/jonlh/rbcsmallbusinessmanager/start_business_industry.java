package com.example.jonlh.rbcsmallbusinessmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static android.R.id.list;

public class start_business_industry extends MainActivity
        implements OnItemSelectedListener{

    DBConnection dbConnection;
    Connection connection;
    PreparedStatement stmt1,stmt2;
    ResultSet rs1,rs2;
    Spinner industrySpinner;

    private TextView industryTextView;
    private ListView industryDetailsListView;
    private Button continueButton2;
    public int industryName;

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String Cat_Name = industrySpinner.getSelectedItem().toString();
        industryName = (int) parent.getItemIdAtPosition(pos); //casting the position that was retrieved as a long into an int
        ArrayList<String> data2 = new ArrayList<String>();
        try {
            connection = dbConnection.CONN();
            stmt2 = connection.prepareStatement("SELECT X FROM (SELECT BusCatID,BusCatName,BusCatDesc,BusCatExmpl,RqrdInvst,MinPeople FROM Business_Category where BusCatName = '" + Cat_Name + "' ) AS cp UNPIVOT ( X FOR Xs IN (BusCatDesc,BusCatExmpl,RqrdInvst,MinPeople)) AS up;");
            rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                String id2 = rs2.getString("X");
                data2.add(id2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            if(industryName == i){
                industryDetailsListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data2));
            }

        }
    }
    public void onNothingSelected(AdapterView<?> parent) {    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_business_industry);

        //calling the components from the view to be used
        industryTextView = (TextView) findViewById(R.id.industryTextView);
        industryDetailsListView = (ListView) findViewById(R.id.industryDetailsListView);
        industrySpinner = (Spinner) findViewById(R.id.industrySpinner);
        continueButton2 = (Button) findViewById(R.id.continueButton);

        //TODO: DATABASE - the selection array should be called from the database as opposed to a hardcoded one (possibly use the primary keys)
//        ArrayAdapter<CharSequence> spinnerAdapter = //creating the array adapter for the spinner
//                ArrayAdapter.createFromResource(this, R.array.industry_array, android.R.layout.simple_spinner_item);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //spinner selection display
//        industrySpinner.setAdapter(spinnerAdapter); //connecting spinner to adapter

        industrySpinner.setOnItemSelectedListener(this); //attaching the item selected method to the Spinner
        initializeText(); //filling in the TextViews dbConnection = new DBConnection();
        dbConnection = new DBConnection();
        connection = dbConnection.CONN();   //initialize DB connection
        if (connection == null){
            System.out.println("Error in connection");
        }
        else {
            System.out.println("DB connection Success");
            try {
                connection = dbConnection.CONN();
                stmt1 = connection.prepareStatement("Select BusCatName from Business_Category");
                rs1 = stmt1.executeQuery();
                ArrayList<String> data = new ArrayList<String>();
                while (rs1.next()) {
                    String id = rs1.getString("BusCatName");
                    data.add(id);
                }
                String[] array = data.toArray(new String[0]);
                ArrayAdapter NoCoreAdapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, data);
                industrySpinner.setAdapter(NoCoreAdapter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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