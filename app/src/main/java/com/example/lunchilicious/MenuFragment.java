package com.example.lunchilicious;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MenuFragment extends Fragment{
    private MenuViewModel viewModel;
    private ExAdapter recycleViewAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    Button addB;

    public static MenuFragment newInstance(){return new MenuFragment();}
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this.requireActivity());
        //final MutableLiveData<ArrayList<ExItem>> menu = viewModel.getMenuItemsLiveData();
        recyclerView.setLayoutManager(layoutManager);
        viewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        /*recycleViewAdapter = new ExAdapter(menu);
        recyclerView.setAdapter(recycleViewAdapter);
        viewModel.getMenuItemsLiveData().observe(this, new Observer<ArrayList<ExItem>>() {
            @Override
            public void onChanged(ArrayList<ExItem> exItems) {
                recycleViewAdapter.setMenuItems(menu);
            }
        });
        addB = addB.findViewById();
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
                        int newId = viewModel.getLastId() + 1;
                        String newName = Ename.getText().toString();
                        String newType = Etype.getText().toString();
                        String newPriceS = Eprice.getText().toString();
                        Float newPriceF = Float.valueOf(newPriceS);
                        Toast.makeText(getApplicationContext(),newName,Toast.LENGTH_SHORT);
                        Toast.makeText(getApplicationContext(),newType,Toast.LENGTH_SHORT);
                        Toast.makeText(getApplicationContext(),newPriceS,Toast.LENGTH_SHORT);
                        ExItem userItem = new ExItem(newId, newType, newName,"", newPriceF);
                        viewModel.addMenuItem(userItem);
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
        });*/
        return view;
    }
}
