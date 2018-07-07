package com.purplechai.admin.kissanyojnaapp;

/**
 * Created by Harish on 25-05-2018.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoInternetConnection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        if (DetectConnection.checkInternetConnection(this)) {
            //Toast.makeText(getApplicationContext(), "No Internet Connection!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(NoInternetConnection.this, MainActivity.class));
            finish();
        }
    }
}