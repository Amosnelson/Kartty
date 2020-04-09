package com.example.kartty;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kartty.Adapter.CartAdapter;
import com.example.kartty.Fragment.MyCartFragment;
import com.example.kartty.Model.CartItem;

import java.util.ArrayList;
import java.util.List;


public class DeliveryActivity extends AppCompatActivity {


    private RecyclerView deliveryRecyclerView;
    private Button changeOraddNewAddressBtn;

    public static final int SELECT_ADDRESS=0;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delivery");

        deliveryRecyclerView = findViewById(R.id.delivery_recyclerview);
        changeOraddNewAddressBtn = findViewById(R.id.change_or_add_address_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryRecyclerView.setLayoutManager(layoutManager);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(0,R.drawable.iphone,"Pixel 2",2,"$5499/-","49999/-",2,0,0));
        cartItemList.add(new CartItem(0,R.drawable.iphone,"Pixel 2",0,"$5499/-","49999/-",2,1,0));
        cartItemList.add(new CartItem(0,R.drawable.iphone,"Pixel 2",2,"$5499/-","49999/-",2,2,0));
        cartItemList.add(new CartItem(1,"Price(3 items)","Rs.169999/-","Free","$4999/-","20999"));
        CartAdapter cartAdapter = new CartAdapter(cartItemList);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        changeOraddNewAddressBtn.setVisibility(View.VISIBLE);

        changeOraddNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myAddressIntent = new Intent(DeliveryActivity.this,MyAddressActivity.class);
                myAddressIntent.putExtra("MODE",SELECT_ADDRESS);
                startActivity(myAddressIntent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
       if (id==android.R.id.home)
       {
           finish();
           return true;
       }


        return super.onOptionsItemSelected(item);
    }
}
