package com.example.dhwani.loginactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    Button btnsubmit;
    private EditText edoldpwd, ednewpwd, edconfirmpwd;
    private String oldpwd, newpwd, confirmpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent x = getIntent();
        Bundle extras = x.getExtras();
        String Id = "";
        try {
            Id = extras.getString("SESSION_ID");
            Toast.makeText(this, "ID = " + Id, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Message = " + e, Toast.LENGTH_LONG).show();
        }


        edoldpwd = (EditText) findViewById(R.id.oldpwd);
        ednewpwd = (EditText) findViewById(R.id.newpwd);
        edconfirmpwd = (EditText) findViewById(R.id.confirmpwd);

        btnsubmit = (Button) findViewById(R.id.submit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

    }

    private void submit() {
        intialize();
        if (!validate()) {
            Toast.makeText(this, "Password doesn't mstch", Toast.LENGTH_SHORT).show();
        } else {
            Onwritepwd();
        }
    }

    public void Onwritepwd() {

    }

    public boolean validate() {
        boolean valid = true;
        if (oldpwd.isEmpty()) {
            edoldpwd.setError("please enter your old password");
            valid = false;
        }
        if (newpwd.isEmpty()) {
            ednewpwd.setError("please enter your new password");
            valid = false;
        }
        if (confirmpwd.isEmpty()) {
            edconfirmpwd.setError("please enter password");
            valid = false;
        }
        return valid;


    }

    private void intialize() {
        oldpwd = edoldpwd.getText().toString().trim();
        newpwd = ednewpwd.getText().toString().trim();
        confirmpwd = edconfirmpwd.getText().toString().trim();
    }
}
