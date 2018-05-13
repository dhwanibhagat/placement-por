package com.example.dhwani.loginactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.builder.Builders;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactUsActivity extends AppCompatActivity {

    final String url = "https://karankeapi.000webhostapp.com/slimapp/public/index.php/api/feedback/";
    Button submit;
    RequestQueue requestQueue;
    EditText editText;
    TextView _response;
    public String TAG = "response";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contactus);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Submitted...");

        _response = findViewById(R.id.textResponse);

        submit = findViewById(R.id.btnsub);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent x = getIntent();
                Bundle extras = x.getExtras();
                String Id = "";
                try {
                    Id = extras.getString("SESSION_ID");
                    Toast.makeText(ContactUsActivity.this, "ID = " + Id, Toast.LENGTH_SHORT).show();

                    editText = findViewById(R.id.editText7);
                    String message = editText.getText().toString();
                    sendUserFeedBack(url, Id, message);
                } catch (Exception e) {
                    Toast.makeText(ContactUsActivity.this, "Message = " + e, Toast.LENGTH_LONG).show();
                }
            }


            public void sendUserFeedBack(String URL, final String uid, final String message) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(ContactUsActivity.this);

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the response string.
                                _response.setText(response);
                                Log.d(TAG, " " + response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        _response.setText("That didn't work!");
                        Log.d("Error", " " + error);
                    }
                }) {
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("uid", uid);
                        params.put("message", message);
                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}








