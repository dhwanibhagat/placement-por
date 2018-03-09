package com.example.dhwani.loginactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.imangazaliev.circlemenu.CircleMenu;
import com.imangazaliev.circlemenu.CircleMenuButton;

public class JobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);


        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circleMenu);
        circleMenu.setOnItemClickListener(new CircleMenu.OnItemClickListener() {
            @Override
            public void onItemClick(CircleMenuButton menuButton) {
                switch (menuButton.getId()) {
                    case R.id.searchjob:
                        Intent search = new Intent(JobActivity.this, SearchActivity.class);
                        startActivity(search);

                        break;
                    case R.id.savejob:
                        Intent save = new Intent(JobActivity.this, SaveActivity.class);
                        startActivity(save);

                        break;
                    case R.id.applyjob:
                        Intent apply = new Intent(JobActivity.this, ApplyActivity.class);
                        startActivity(apply);

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

