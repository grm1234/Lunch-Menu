package com.example.lunchilicious;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface menuItem {

    @GET("/lunchilicious/menuitems")
    Call<List<ExItem>> reposForUser();

    @POST("/lunchilicious/addmenuitem")
    Call<ExItem> addMenuItem(@Body ExItem item);

}