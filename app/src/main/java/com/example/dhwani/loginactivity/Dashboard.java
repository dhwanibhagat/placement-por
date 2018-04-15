package com.example.dhwani.loginactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    public String UserId;
    private CardView profile, job, contact, setting, share, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Getting and storing Variable from previous Activity
        // Session Like
        Intent x = getIntent();
        Bundle extras = x.getExtras();
        String Id = extras.getString("SESSION_ID");
        Toast.makeText(this, "ID = " + Id, Toast.LENGTH_SHORT).show();
        UserId = Id;
        // End Here

        profile = (CardView) findViewById(R.id.profile);
        job = (CardView) findViewById(R.id.job);
        contact = (CardView) findViewById(R.id.contact);
        setting = (CardView) findViewById(R.id.setting);
        share = (CardView) findViewById(R.id.share);
        logout = (CardView) findViewById(R.id.logout);

        profile.setOnClickListener(this);
        /*
        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, JobActivity.class);
                startActivity(i);

            }
        });*/
        job.setOnClickListener(this);
        contact.setOnClickListener(this);
        setting.setOnClickListener(this);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("Text/plain");
                String shareBody = "body";
                String shareSub = "subject";
                myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                startActivity(Intent.createChooser(myIntent, "Share Using"));


            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplication(), "Successfully Logout", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Dashboard.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i;


        switch (view.getId()) {
            case R.id.profile:
                i = new Intent(this, ProfileActivity.class);
                i.putExtra("SESSION_ID", UserId);
                startActivity(i);
                break;
            case R.id.job:
                i = new Intent(this, JobActivity.class);
                i.putExtra("SESSION_ID", UserId);
                startActivity(i);
                break;
            case R.id.contact:
                i = new Intent(this, ContactUsActivity.class);
                i.putExtra("SESSION_ID", UserId);
                startActivity(i);
                break;
            case R.id.setting:
                i = new Intent(this, SettingsActivity.class);
                i.putExtra("SESSION_ID", UserId);
                startActivity(i);
                break;
            case R.id.share:
                i = new Intent(this, ShareAppActivity.class);
                i.putExtra("SESSION_ID", UserId);
                startActivity(i);
                break;
            case R.id.logout:
                i = new Intent(this, LogoutActivity.class);
                i.putExtra("SESSION_ID", UserId);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
