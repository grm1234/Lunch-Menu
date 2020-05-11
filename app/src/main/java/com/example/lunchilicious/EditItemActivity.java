package com.example.lunchilicious;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.lunchilicious.EXTRA_ID";
    public static final String EXTRA_NAME = "com.example.lunchilicious.EXTRA_NAME";
    public static final String EXTRA_TYPE = "com.example.lunchilicious.EXTRA_TYPE";
    public static final String EXTRA_PRICE = "com.example.lunchilicious.EXTRA_PRICE";
    public static final String EXTRA_DESC = "com.example.lunchilicious.EXTRA_DESC";

    private EditText EtName;
    private EditText EtType;
    private EditText EtPrice;
    private EditText EtDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        EtName = findViewById(R.id.NameET);
        EtType = findViewById(R.id.TypeET);
        EtPrice = findViewById(R.id.PriceET);
        EtDesc = findViewById(R.id.DescET);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Update Note");
    }

    private void saveItem()
    {
        String name = EtName.getText().toString();
        String type = EtType.getText().toString();
        String desc = EtDesc.getText().toString();
        String price = EtPrice.getText().toString();

        if(name.trim().isEmpty() || type.trim().isEmpty() || price.trim().isEmpty() ||
                desc.trim().isEmpty()){
            Toast.makeText(this, "Temp", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_TYPE, type);
        data.putExtra(EXTRA_PRICE, price);
        data.putExtra(EXTRA_DESC, desc);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id!= -1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK, data);
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_item:
            saveItem();
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
