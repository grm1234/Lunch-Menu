package com.example.lunchilicious;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MenuItemDao {
    @Insert
    void insert(ExItem menuItem);

    @Insert
    void insertLst(List<ExItem> menuItem);

    @Update
    void update(ExItem menuItem);

    @Delete
    void delete(ExItem menuItem);

    @Query("DELETE FROM item")
    void deleteAllItems();

    @Query( "SELECT * FROM item ORDER BY mType, mName")
    LiveData<List<ExItem>> getAllMenuItems();

    @Query("SELECT * FROM item WHERE id = :id")
    LiveData<List<ExItem>> getItem(int id);

    //@Query("SELECT MAX(id) FROM item")
    //LiveData<List<ExItem>> getLast();
}
