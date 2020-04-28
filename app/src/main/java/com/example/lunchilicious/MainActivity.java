package com.example.lunchilicious;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  implements LifecycleOwner {

    private MenuViewModel menuViewModel;
    Button addB;
    Button deleteB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ExAdapter adapter = new ExAdapter();
        final int lastId = adapter.getItemCount();
        recyclerView.setAdapter(adapter);
        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        menuViewModel.getItemData().observe(this, new Observer<List<ExItem>>() {
            @Override
            public void onChanged(List<ExItem> exItems) {
                adapter.setMenuItems(exItems);
            }
        });
        addB = findViewById(R.id.AddItemB);
        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View promptView = layoutInflater.inflate(R.layout.prompt,null);
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
                alertDialog.setView(promptView);
                final EditText Ename = (EditText)promptView.findViewById(R.id.nameE);
                final EditText Etype = (EditText)promptView.findViewById(R.id.typeE);
                final EditText Eprice = (EditText)promptView.findViewById(R.id.priceE);
                alertDialog.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int newId = lastId + 1;
                        String newName = Ename.getText().toString();
                        String newType = Etype.getText().toString();
                        String newPriceS = Eprice.getText().toString();
                        Float newPriceF = Float.valueOf(newPriceS);
                        Toast.makeText(getApplicationContext(),newName,Toast.LENGTH_SHORT);
                        Toast.makeText(getApplicationContext(),newType,Toast.LENGTH_SHORT);
                        Toast.makeText(getApplicationContext(),newPriceS,Toast.LENGTH_SHORT);
                        ExItem userItem = new ExItem(newId, newType, newName,"", newPriceF);
                        menuViewModel.insert(userItem);
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.create();
                alertDialog.show();
            }
        });
        deleteB = findViewById(R.id.DeleteItemB);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View promptView = layoutInflater.inflate(R.layout.prompt,null);
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
                alertDialog.setView(promptView);
                final EditText Eid = (EditText)promptView.findViewById(R.id.IdE);
                final EditText Ename = (EditText)promptView.findViewById(R.id.nameE);
                final EditText Etype = (EditText)promptView.findViewById(R.id.typeE);
                final EditText Eprice = (EditText)promptView.findViewById(R.id.priceE);
                alertDialog.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        String newIdS = Eid.getText().toString();
                        int newId = Integer.valueOf(newIdS);
                        if(newId == 0){
                            menuViewModel.deleteAllItems();
                        }else{ String newName = Ename.getText().toString();
                        String newType = Etype.getText().toString();
                        String newPriceS = Eprice.getText().toString();
                        Float newPriceF = Float.valueOf(newPriceS);
                        Toast.makeText(getApplicationContext(),newName,Toast.LENGTH_SHORT);
                        Toast.makeText(getApplicationContext(),newType,Toast.LENGTH_SHORT);
                        Toast.makeText(getApplicationContext(),newPriceS,Toast.LENGTH_SHORT);
                        ExItem userItem = new ExItem(newId, newType, newName,"", newPriceF);
                        menuViewModel.delete(userItem);}
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.create();
                alertDialog.show();
            }
        });

        /*final RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        final Observer<ArrayList<ExItem>> itemObserver = new Observer<ArrayList<ExItem>>() {
            @Override
            public void onChanged(ArrayList<ExItem> exItems) {
                if(menu == null){
                    menu = viewModel.getMenuItemsLiveData().observe(getLifecycle(), itemObserver);
                    mAdapter = new ExAdapter(context, exItems);
                    mRecyclerView.setAdapter(mAdapter);
                }else{

                }
            }
        };*/
        /*if(menu != null){
            viewModel.getMenuItemsLiveData().observe(this, itemsObserver);
        }*/
        //viewModel.getMenuItemsLiveData().observe(this, itemListUpdateObserver);
        //mRecyclerView.setHasFixedSize(true);
        //mAdapter = new ExAdapter(menu);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        //mRecyclerView.setAdapter(mAdapter);
    }


}
