package com.example.dhwani.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ShareAppActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareapp);

        Intent x = getIntent();
        Bundle extras = x.getExtras();
        String Id = "";
        try {
            Id = extras.getString("SESSION_ID");
            Toast.makeText(this, "ID = " + Id, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Message = " + e, Toast.LENGTH_LONG).show();
        }

    }
}
