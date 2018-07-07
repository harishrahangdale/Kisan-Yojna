package com.purplechai.admin.kissanyojnaapp;

/**
 * Created by Harish on 18-05-2018.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class SelectStateActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private FirebaseAuth mAuth;
    String PREFS_NAME = "auth_info";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);

        /*if (!DetectConnection.checkInternetConnection(this)) {
            //Toast.makeText(getApplicationContext(), "No Internet Connection!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(SelectStateActivity.this, NoInternetConnection.class));
            finish();
        }
*/
        SharedPreferences sharedPreferences= getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String proceed = sharedPreferences.getString("flag", "0");

        mAuth = FirebaseAuth.getInstance();

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Button button=(Button)findViewById(R.id.button);

        /*TextView testing = (TextView) findViewById(R.id.testing);
        testing.setText(proceed);*/

       // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Andhra Pradesh/आंध्र प्रदेश");
        categories.add("Arunachal Pradesh/अरुणाचल प्रदेश");
        categories.add("Assam/असम");
        categories.add("Bihar/बिहार");
        categories.add("Chattisgarh/छत्तीसगढ़");
        categories.add("Goa/गोवा");
        categories.add("Gujarat/गुजरात");
        categories.add("Haryana/हरियाणा");
        categories.add("Himachal Pradesh/हिमाचल प्रदेश");
        categories.add("Jammu Kashmir/जम्मू कश्मीर");
        categories.add("Jharkhand/झारखंड");
        categories.add("Karnataka/कर्नाटक");
        categories.add("Kerala/केरल");
        categories.add("Madhya Pradesh/मध्य प्रदेश");
        categories.add("Maharashtra/महाराष्ट्र");
        categories.add("Manipur/मणिपुर");
        categories.add("Meghalaya/मेघालय");
        categories.add("Mizoram/मिजोरम");
        categories.add("Nagaland/नगालैंड");
        categories.add("Odisha/ओडिशा");
        categories.add("Punjab/पंजाब");
        categories.add("Rajashtan/राजस्थान");
        categories.add("Sikkim/सिक्किम");
        categories.add("Tamil Nadu/तमिलनाडु");
        categories.add("Telangana/तेलंगाना");
        categories.add("Tripura/त्रिपुरा");
        categories.add("Uttar Pradesh/उत्तर प्रदेश");
        categories.add("Uttarakhand/उत्तराखंड");
        categories.add("West Bengal/पश्चिम बंगाल");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        /*String str =String.valueOf(spinner.getSelectedItem());
        String kept = str.substring( 0, str.indexOf("/"));
        String remainder = str.substring(str.indexOf("/")+1, str.length());*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String mainstring = String.valueOf(spinner.getSelectedItem());
                String substring = mainstring.substring( 0, mainstring.indexOf("/"));
                editor.putString("savedstate",substring);
                editor.apply();

                if (mAuth.getCurrentUser() != null) {
                    if (proceed.equals("1")) {
                        startActivity(new Intent(SelectStateActivity.this, MainActivity.class));
                        finish();
                    }
                    else {
                        mAuth.signOut();
                        Intent intent = new Intent(SelectStateActivity.this, GoogleSignInActivity.class);
                        intent.putExtra("state", substring);
                        startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(SelectStateActivity.this, GoogleSignInActivity.class);
                    intent.putExtra("state", substring);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}