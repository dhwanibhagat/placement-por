package com.example.dhwani.loginactivity;

import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dhwani on 3/20/2018.
 */

public interface Api {
    String BASE_URL = "https://karankeapi.000webhostapp.com/slimapp/public/index.php/api/";

    @GET("getCandidates")
    Call<List<names>> getnames();
}
