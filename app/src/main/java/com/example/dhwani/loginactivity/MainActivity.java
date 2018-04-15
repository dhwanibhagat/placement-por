package com.example.dhwani.loginactivity;

import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";
    private static final String URL = "https://karankeapi.000webhostapp.com/slimapp/public/index.php/api/login/";
    public int userId;
    ProgressBar progressBar;
    RelativeLayout relativeLayout;
    AppCompatEditText email, password;
    TextInputLayout emailLayout, passwordLayout;
    Button login;
    String passwordEnc;
    Session session;
    private StringRequest request;
    private RequestQueue requestQueue;

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(getApplicationContext());

        requestQueue = Volley.newRequestQueue(this);

        relativeLayout = findViewById(R.id.relative);

        relativeLayout.setOnClickListener(null);
        login = findViewById(R.id.submit);

        email = findViewById(R.id.Email_textfield);
        password = findViewById(R.id.password_textfield);
        passwordEnc = password.getText().toString();

        emailLayout = findViewById(R.id.Email_Textinputlayout);
        passwordLayout = findViewById(R.id.password_Textinputlayout);

        emailLayout.setCounterEnabled(true);
        emailLayout.setCounterMaxLength(50);

        passwordLayout.setCounterEnabled(true);
        passwordLayout.setCounterMaxLength(50);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
                    emailLayout.setErrorEnabled(true);
                    emailLayout.setError("Please enter Email");

                    passwordLayout.setErrorEnabled(true);
                    passwordLayout.setError("Please enter password");

                } else if (email.getText().toString().isEmpty()) {
                    emailLayout.setErrorEnabled(true);
                    emailLayout.setError("Please enter Email");
                } else if (password.getText().toString().isEmpty()) {
                    passwordLayout.setErrorEnabled(true);
                    passwordLayout.setError("Please enter password");
                } else {
                    if (isValidEmail(email.getText().toString())) {
                        emailLayout.setErrorEnabled(false);
                        passwordLayout.setErrorEnabled(false);
                        //JSON CODE
                        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    //LOGIN

                                    if (jsonObject.names().get(0).equals("success")) {
                                        Toast.makeText(getApplicationContext(), "SUCCESS" + jsonObject.getInt("ID"), Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), Dashboard.class);

                                        String x = Integer.toString(jsonObject.getInt("ID"));

                                        i.putExtra("SESSION_ID", x);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Wrong details" + jsonObject.getString("notfound"), Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("email", email.getText().toString());
                                hashMap.put("password", password.getText().toString());
                                return hashMap;
                            }
                        };

                        requestQueue.add(request);

                    } else {
                        emailLayout.setErrorEnabled(true);
                        emailLayout.setError("Please enter valid email");
                    }

                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (email.getText().toString().isEmpty()) {
                    emailLayout.setErrorEnabled(true);
                    emailLayout.setError("Please enter you email");
                } else {
                    emailLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (email.getText().toString().isEmpty()) {
                    emailLayout.setErrorEnabled(true);
                    emailLayout.setError("Please enter you email");
                } else {
                    emailLayout.setErrorEnabled(false);
                }
            }
        });
    }
}
