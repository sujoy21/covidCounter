package com.example.covid;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OurRetrofitClient {

    @GET("data.json")
    Call<Response>getData();

}
