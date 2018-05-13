package com.example.dhwani.loginactivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.imangazaliev.circlemenu.CircleMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ApplyActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://karankeapi.000webhostapp.com/slimapp/public/index.php/api/getJobs/";
    private static final int NUM_OF_TEXTS = 10;
    private RecyclerView rv1;
    private RecyclerView.Adapter adapter;

    private List<ListItem1>listItem1s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_apply);

        rv1 = (RecyclerView)findViewById(R.id.rv1);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        listItem1s = new ArrayList<>();

        loadRecyclerViewData();

        }

        private void loadRecyclerViewData(){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading data...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URL_DATA,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            progressDialog.dismiss();
                            try {
                                JSONArray array = new JSONArray(s);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject o = array.getJSONObject(i);
                                    ListItem1 item = new ListItem1(
                                            o.getString("job_name"),
                                            o.getString("company_name")
                                    );
                                    listItem1s.add(item);
                                }
                                adapter = new MyAdaptor1(listItem1s, getApplicationContext());
                                rv1.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        Intent x = getIntent();
        Bundle extras = x.getExtras();
        String Id = "";
}

