package com.example.lunchilicious;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity  implements LifecycleOwner {
    String BASE_URL = "http://aristotle.cs.scranton.edu/";
    String DEFAULT_USER = "dbuser1";
    public static final int EDIT_ITEM_REQUEST = 1;
    private MenuViewModel menuViewModel;
    Button addB;
    Button umB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
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
        umB = findViewById(R.id.UmButton);
        umB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.client(httpclient.build()).build();
                final menuItem menuitem = retrofit.create(menuItem.class);
                Call<List<ExItem>> call = menuitem.reposForUser();
                call.enqueue(new Callback<List<ExItem>>() {
                    @Override public void onResponse(Call<List<ExItem>> call, Response<List<ExItem>> response) {
                        List<ExItem> menuList = response.body();
                        adapter.setMenuItems(menuList);
                    }

                    @Override public void onFailure(Call<List<ExItem>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
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
                                menuViewModel.insert(userItem);
                                //adding to central db
                                OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
                                Retrofit.Builder builder = new Retrofit.Builder()
                                        .baseUrl(BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create());
                                Retrofit retrofit = builder.client(httpclient.build()).build();
                                final menuItem menuitem = retrofit.create(menuItem.class);
                                Call<ExItem> call = menuitem.addMenuItem(userItem);
                                call.enqueue(new Callback<ExItem>() {
                                    @Override public void onResponse(Call<ExItem> call, Response<ExItem> response) {
                                        ExItem menuList = response.body();
                                        Toast.makeText(MainActivity.this, menuList.mName, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override public void onFailure(Call<ExItem> call, Throwable t) {
                                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
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
        adapter.setOnItemCLickListener(new ExAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ExItem menuItem) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra(EditItemActivity.EXTRA_ID, menuItem.getId());
                intent.putExtra(EditItemActivity.EXTRA_NAME, menuItem.getmName());
                intent.putExtra(EditItemActivity.EXTRA_TYPE, menuItem.getmType());
                intent.putExtra(EditItemActivity.EXTRA_PRICE, menuItem.getmPrice());
                intent.putExtra(EditItemActivity.EXTRA_DESC, menuItem.getmDescription());
                startActivityForResult(intent, EDIT_ITEM_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_ITEM_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(EditItemActivity.EXTRA_ID, -1);
                    if(id == -1){Toast.makeText(this, "Item Can't Be Updated", Toast.LENGTH_SHORT).show();
                    return;}
            String name = data.getStringExtra(EditItemActivity.EXTRA_NAME);
            String type = data.getStringExtra(EditItemActivity.EXTRA_TYPE);
            String priceS = data.getStringExtra(EditItemActivity.EXTRA_PRICE);
            String desc = data.getStringExtra(EditItemActivity.EXTRA_DESC);
            Float pricef = Float.valueOf(priceS);
            ExItem menuItem = new ExItem(name, type, desc, pricef);
            menuItem.setId(id);
            menuViewModel.update(menuItem);
            Toast.makeText(this, "Item Updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Item Not Saved", Toast.LENGTH_SHORT).show();
        }
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