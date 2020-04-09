package com.example.kartty.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kartty.Adapter.CartAdapter;
import com.example.kartty.AddActivity;
import com.example.kartty.Model.CartItem;
import com.example.kartty.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {

    public MyCartFragment()
    {
        // Required empty public constructor
    }
    private RecyclerView cartItemRecyclerView;

    private Button continueBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_my_cart, container, false);
        cartItemRecyclerView = view.findViewById(R.id.cart_item_recyclerview);
        continueBtn = view.findViewById(R.id.cart_continue_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerView.setLayoutManager(layoutManager);

        List<CartItem>cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem(0,R.drawable.iphone,"Pixel 2",2,"$5499/-","49999/-",2,0,0));
        cartItemList.add(new CartItem(0,R.drawable.iphone,"Pixel 2",0,"$5499/-","49999/-",2,1,0));
        cartItemList.add(new CartItem(0,R.drawable.iphone,"Pixel 2",2,"$5499/-","49999/-",2,2,0));
        cartItemList.add(new CartItem(1,"Price(3 items)","Rs.169999/-","Free","$4999/-","20999"));
        CartAdapter cartAdapter = new CartAdapter(cartItemList);
        cartItemRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();



        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deliveryIntent = new Intent(getContext(), AddActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });
        return view;
    }
}
