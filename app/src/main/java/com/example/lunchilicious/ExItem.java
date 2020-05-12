package com.example.lunchilicious;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "item")
public class ExItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("type")
    public String mType;
    @SerializedName("name")
    public String mName;
    @SerializedName("description")
    public String mDescription;

    @ColumnInfo(name = "price")
    public float unitPrice;

    public ExItem(String mType, String mName, String mDescription, float unitPrice){
        this.mType = mType;
        this.mName = mName;
        this.mDescription = mDescription;
        this.unitPrice = unitPrice;
    }
    public void setId(int id){ this.id = id;}
    public int getId(){return id;}
    public String getmType(){return mType;}
    public String getmName(){return mName;}
    public String getmDescription(){return mDescription;}
    public float getmPrice() {return unitPrice;}
}


