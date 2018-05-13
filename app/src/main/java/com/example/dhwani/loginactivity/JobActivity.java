package com.example.dhwani.loginactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.imangazaliev.circlemenu.CircleMenu;
import com.imangazaliev.circlemenu.CircleMenuButton;

public class JobActivity extends AppCompatActivity {

    String userId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_job);

        Intent x = getIntent();
        Bundle extras = x.getExtras();
        String Id = extras.getString("SESSION_ID");
        Toast.makeText(this, "ID = " + Id, Toast.LENGTH_SHORT).show();

        userId = Id;


        String url = "https://karankeapi.000webhostapp.com/slimapp/public/index.php/api/getCandidatePersonalDetails/" + Id;


        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circleMenu);
        circleMenu.setOnItemClickListener(new CircleMenu.OnItemClickListener() {
            @Override
            public void onItemClick(CircleMenuButton menuButton) {
                Intent i;
                switch (menuButton.getId()) {

                    case R.id.savejob:
                        i = new Intent(JobActivity.this, SaveActivity.class);
                        i.putExtra("SESSION_ID",userId);
                        startActivity(i);
                        break;
                    case R.id.applyjob:
                        i = new Intent(JobActivity.this, ApplyActivity.class);
                        i.putExtra("SESSION_ID",userId);
                        startActivity(i);
                        break;
                }
            }
        });

        circleMenu.setStateUpdateListener(new CircleMenu.OnStateUpdateListener() {
            @Override
            public void onMenuExpanded() {
                Log.d("CircleMenuStatus", "Expanded");
            }

            @Override
            public void onMenuCollapsed() {
                Log.d("CircleMenuStatus", "collapsed");

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

