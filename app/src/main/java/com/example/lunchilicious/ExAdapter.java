package com.example.lunchilicious;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExAdapter extends RecyclerView.Adapter<ExAdapter.ExViewHolder> {
    private List<ExItem> MenuList;
    //private List<ExItem> MenuList = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;

    public  class ExViewHolder extends RecyclerView.ViewHolder{
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
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (listener != null && pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(MenuList.get(pos));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ExViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_one,parent, false);
        ExViewHolder evh = new ExViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(@NonNull ExViewHolder holder, int position){
        if(MenuList.get(position)!=null){
            ExItem currentItem = MenuList.get(position);
            holder.mNameTV.setText(currentItem.getmName());
            holder.mTypeTV.setText(currentItem.getmType());
            holder.mPriceTV.setText(Float.toString(currentItem.getmPrice()));
            holder.mDescTV.setText(currentItem.getmDescription());
        }
    }
   @Override
    public int getItemCount(){
        return MenuList.size();
    }

    public void setMenuItems(List<ExItem> menuItems) {
        this.MenuList = menuItems;
        notifyDataSetChanged();
    }
    public ExItem getItemAt(int position){
        return MenuList.get(position);
    }

    public interface OnItemClickListener{
        void onItemClick(ExItem menuItem);
    }

    public void setOnItemCLickListener(OnItemClickListener listener){
        this.listener =listener;
    }
}
