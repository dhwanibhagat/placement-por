package com.example.dhwani.loginactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Executable;

import retrofit2.http.Url;


public class ProfileActivity extends AppCompatActivity {
    RequestQueue rq;

    TextView textname, textadd, textdob, textemail;
    String fullname, s_email, c_add, dob;


//    Intent x = getIntent();
//    Bundle extras = x.getExtras();
//    public String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        Intent x = getIntent();
        Bundle extras = x.getExtras();
        String Id = "";
        try {
            Id = extras.getString("SESSION_ID");
            Toast.makeText(this, "ID = " + Id, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Message = " + e, Toast.LENGTH_LONG).show();
        }

        String url = "https://karankeapi.000webhostapp.com/slimapp/public/index.php/api/getCandidatePersonalDetails/" + Id;


//        if(extras!=null){
//            Id = extras.getString("SESSION_ID");
//            Toast.makeText(this, Id, Toast.LENGTH_SHORT).show();
//        }

        rq = Volley.newRequestQueue(this);

        textname = findViewById(R.id.textname);
        textadd = findViewById(R.id.textadd);
        textdob = findViewById(R.id.textdob);
        textemail = findViewById(R.id.textemail);

        sendjsonrequest(url);
    }

    public void sendjsonrequest(String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    fullname = response.getString("fullname");
                    c_add = response.getString("c_add");
                    s_email = response.getString("s_email");
                    dob = response.getString("dob");

                    textname.setText(fullname);
                    textadd.setText(c_add);
                    textdob.setText(dob);
                    textemail.setText(s_email);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });

        rq.add(jsonObjectRequest);
    }
}