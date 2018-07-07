package com.purplechai.admin.kissanyojnaapp;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harish on 14-06-2018.
 */

public class NavMenuItemSelect extends AppCompatActivity {

    //RecyclerView
    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mKYData;
    private AdapterHomeData mAdapter;

    String PREFS_NAME = "auth_info";
    String savedstate;
    String menuname1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_menu_select);

        SharedPreferences sharedPreferences= getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        savedstate=sharedPreferences.getString("savedstate", null);

        //Retrieve the url that you put into your intent
        menuname1 = getIntent().getStringExtra("menuname");


        //Make call to AsyncTask
        new AsyncFetch().execute();

    }

    //RecyclerView
    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(NavMenuItemSelect.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("https://kisanyojna.000webhostapp.com/KY_Api/getData.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            List<DataKY> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);

                // Extract data from json and store into ArrayList as class objects
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    DataKY kyData = new DataKY();

                    String state1 = json_data.getString("state");
                    String menu1 = json_data.getString("menu");
                    //simple if statement allows only those jsonObjects to be added the laptopList where price is less than 40000.
                    if (state1.equals(savedstate) && menu1.equals(menuname1)) {

                        kyData.kyimage = json_data.getString("img");
                        kyData.title = json_data.getString("title");
                        kyData.des = json_data.getString("des");
                        kyData.webinfo = json_data.getString("webinfo");
                        kyData.menuname = json_data.getString("menu");
                        data.add(kyData);

                    }

                }
                // Setup and Handover data to recyclerview
                mKYData = (RecyclerView)findViewById(R.id.data);
                mAdapter = new AdapterHomeData(NavMenuItemSelect.this, data);
                mKYData.setAdapter(mAdapter);
                mKYData.setLayoutManager(new LinearLayoutManager(NavMenuItemSelect.this));

            } catch (JSONException e) {
                Toast.makeText(NavMenuItemSelect.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

}
