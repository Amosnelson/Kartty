package com.example.kartty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.example.kartty.Adapter.GridProductViewAdapter;
import com.example.kartty.Model.HorizontalProductModel;
import com.example.kartty.Model.WishlistModel;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Deals of the Day");

        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_view);

        int layout_code = getIntent().getIntExtra("layout_code", -1);
        if (layout_code == 0) {
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            List<WishlistModel> wishlistModelList = new ArrayList<>();

            wishlistModelList.add(new WishlistModel(R.drawable.iphone, "Pixel 2", 1, "3", 145, "$49999/-", "$5999/-", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.iphone, "Pixel 2", 0, "3", 145, "$49999/-", "$5999/-", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.iphone, "Pixel 2", 2, "3", 145, "$49999/-", "$5999/-", "Cash on delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.iphone, "Pixel 2", 4, "3", 145, "$49999/-", "$5999/-", "Cash on delivery"));


          //  WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList, false);
//            recyclerView.setAdapter(wishlistAdapter);
//            wishlistAdapter.notifyDataSetChanged();
        } else if (layout_code == 1) {


            gridView.setVisibility(View.VISIBLE);
            List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();

            GridProductViewAdapter gridProductViewAdapter = new GridProductViewAdapter(horizontalProductModelList);
            gridView.setAdapter(gridProductViewAdapter);
            gridProductViewAdapter.notifyDataSetChanged();
        }
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

