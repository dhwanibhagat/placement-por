package com.example.dhwani.loginactivity;

import android.annotation.SuppressLint;
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
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    AppCompatEditText email, password;
    TextInputLayout emailLayout, passwordLayout;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        relativeLayout.setOnClickListener(null);
        login = (Button) findViewById(R.id.submit);


        email = (AppCompatEditText) findViewById(R.id.Email_textfield);
        password = (AppCompatEditText) findViewById(R.id.password_textfield);

        emailLayout = (TextInputLayout) findViewById(R.id.Email_Textinputlayout);
        passwordLayout = (TextInputLayout) findViewById(R.id.password_Textinputlayout);

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
                        Intent i = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(i);
                        finish();
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
    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
