package com.example.lunchilicious;

public class ExItem {
    private int id;
    private String mType;
    private String mName;
    private String mDescription;
    private float mPrice;
    public ExItem(int textI, String text1, String text2, String text3, float text4){
        id = textI;
        mType = text1;
        mName = text2;
        mDescription = text3;
        mPrice = text4;
    }
    public int getId(){return id;}
    public String getmType(){return mType;}
    public String getmName(){return mName;}
    public String getmDescription(){return mDescription;}
    public float getmPrice() {return mPrice;}
}


