package com.example.lunchilicious;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface menuItem {
    @GET("/lunchilicious/{user}/menuitems")
    Call<List<ExItem>> reposForUser(@Path("user") String user);
}