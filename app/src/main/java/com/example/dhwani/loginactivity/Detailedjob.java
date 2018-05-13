package com.example.dhwani.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Detailedjob extends AppCompatActivity {

    String UserId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedjob);

        Intent x = getIntent();
        Bundle extras = x.getExtras();
        String Id = extras.getString("SESSION_ID");
        Toast.makeText(this, "ID = " + Id, Toast.LENGTH_SHORT).show();
        UserId = Id;
    }
}
