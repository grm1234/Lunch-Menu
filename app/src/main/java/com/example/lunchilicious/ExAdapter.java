package com.example.lunchilicious;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExAdapter extends RecyclerView.Adapter<ExAdapter.ExViewHolder>{
    private ArrayList<ExItem> MenuList;
    public static class ExViewHolder extends RecyclerView.ViewHolder{
        public TextView mTypeTV;
        public TextView mNameTV;
        public TextView mDescTV;
        public TextView mPriceTV;

        public ExViewHolder(View itemView){
            super(itemView);
            mTypeTV = itemView.findViewById(R.id.textView);
            mNameTV = itemView.findViewById(R.id.textView2);
            mPriceTV = itemView.findViewById(R.id.textView3);
            mDescTV = itemView.findViewById(R.id.textView4);
        }
    }
    public ExAdapter(ArrayList<ExItem> menuList){
        MenuList = menuList;
    }
    @Override
    public ExViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one,parent, false);
        ExViewHolder evh = new ExViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExViewHolder holder, int position){
        ExItem currentItem = MenuList.get(position);
        holder.mNameTV.setText(currentItem.getmName());
        holder.mTypeTV.setText(currentItem.getmType());
        holder.mPriceTV.setText(Float.toString(currentItem.getmPrice()));
        holder.mDescTV.setText(currentItem.getmDescription());
    }
    @Override
    public int getItemCount(){
        return MenuList.size();
    }
}
