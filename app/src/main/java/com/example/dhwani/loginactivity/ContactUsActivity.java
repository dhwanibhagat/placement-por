package com.example.dhwani.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactUsActivity extends AppCompatActivity {

    final String url = "https://karankeapi.000webhostapp.com/slimapp/public/index.php/api/feedback/";
    Button submit;
    RequestQueue requestQueue;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


        submit = findViewById(R.id.btnsub);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = getIntent();
                Bundle extras = x.getExtras();
                String Id = "";


                try {
                    Id = extras.getString("SESSION_ID");
                    Toast.makeText(ContactUsActivity.this, "ID = " + Id, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ContactUsActivity.this, "Message = " + e, Toast.LENGTH_LONG).show();
                }

                editText = findViewById(R.id.editText7);
                String message = editText.getText().toString();
                Toast.makeText(ContactUsActivity.this, "Message = " + message, Toast.LENGTH_SHORT).show();
                Toast.makeText(ContactUsActivity.this, "URL = " + url, Toast.LENGTH_SHORT).show();
                Toast.makeText(ContactUsActivity.this, "User Id = " + Id, Toast.LENGTH_SHORT).show();
                sendUserFeedback(Id, url, message);
            }
        });
    }

    public void sendUserFeedback(final String userId, String url, final String message) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(ContactUsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ContactUsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uid", userId);
                params.put("message", message);
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }
}

