package com.example.lunchilicious;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item")
public class ExItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String mType;
    public String mName;
    public String mDescription;

    @ColumnInfo(name = "price")
    public float mPrice;

    public ExItem(String mType, String mName, String mDescription, float mPrice){
        this.mType = mType;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mPrice = mPrice;
    }
    public void setId(int id){ this.id = id;}
    public int getId(){return id;}
    public String getmType(){return mType;}
    public String getmName(){return mName;}
    public String getmDescription(){return mDescription;}
    public float getmPrice() {return mPrice;}
}


