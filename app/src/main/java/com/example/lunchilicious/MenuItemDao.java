package com.example.lunchilicious;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

@Dao
public interface MenuItemDao {
    @Insert
    void insert(ExItem menuItem);

    @Insert
    void insertList(List<ExItem> menuItem);

    @Update
    void update(ExItem menuItem);

    @Delete
    void delete(ExItem menuItem);

    @Query("DELETE FROM item")
    void deleteAllItems();

    @Query( "SELECT * FROM item ORDER BY mType, mName")
    LiveData<List<ExItem>> getAllMenuItems();


    //@GET("/lunchilicious/menuitems")
    //Call<List<ExItem>> getItems();
}
