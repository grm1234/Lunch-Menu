package com.example.lunchilicious;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
                final EditText DAll = (EditText)promptView.findViewById(R.id.DAET);
                alertDialog.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Dall = DAll.getText().toString();
                        if(Dall.equals("YES")){
                            menuViewModel.deleteAllItems();
                        }else{
                            String newName = Ename.getText().toString();
                            String newType = Etype.getText().toString();
                            String newPriceS = Eprice.getText().toString();
                            if(newPriceS.equals("Price")){
                                return;
                            }else{
                            Float newPriceF = Float.valueOf(newPriceS);
                            Log.d("Lunchilicious", "price" + String.valueOf(newPriceF));
                                Toast.makeText(getApplicationContext(),newName,Toast.LENGTH_SHORT);
                                Toast.makeText(getApplicationContext(),newType,Toast.LENGTH_SHORT);
                                Toast.makeText(getApplicationContext(),newPriceS,Toast.LENGTH_SHORT);
                                ExItem userItem = new ExItem(newType, newName,"", newPriceF);
                                menuViewModel.insert(userItem);}
                        }
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
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                menuViewModel.delete(adapter.getItemAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
    }
}

/*Reserve Code in case I need it again
final RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
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