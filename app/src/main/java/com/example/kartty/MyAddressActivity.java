package com.example.kartty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kartty.Adapter.AddressesAdapter;
import com.example.kartty.Model.AddressesModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.kartty.DeliveryActivity.SELECT_ADDRESS;

public class MyAddressActivity extends AppCompatActivity {

    private RecyclerView myAddressRecyclerView;

    private static AddressesAdapter addressesAdapter;

    private Button deliverHereBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Address");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        deliverHereBtn = findViewById(R.id.deliver_here_btn);

        myAddressRecyclerView = findViewById(R.id.addresses_recyclerview);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList=new ArrayList<>();
        addressesModelList.add(new AddressesModel("John Doe","news engineering dawaki Abuja Nigeria Covid 19","6586543",true));
        addressesModelList.add(new AddressesModel("Mark lizzy","news engineering dawaki Abuja Nigeria Covid 19","6586543",false));
        addressesModelList.add(new AddressesModel("flocky Stack","news engineering dawaki Abuja Nigeria Covid 19","6586543",true));

        int mode = getIntent().getIntExtra("MODE",-1);

        if (mode == SELECT_ADDRESS)
        {
            deliverHereBtn.setVisibility(View.VISIBLE);
        }else
        {
            deliverHereBtn.setVisibility(View.GONE);
        }
         addressesAdapter = new AddressesAdapter(addressesModelList,mode);
        myAddressRecyclerView.setAdapter(addressesAdapter);
        ((SimpleItemAnimator)myAddressRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();


    }

    public static void refreshItem(int deselect,int select){
        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
