package com.example.kartty.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kartty.DeliveryActivity;
import com.example.kartty.MyAddressActivity;
import com.example.kartty.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    public MyAccountFragment() {
        // Required empty public constructor
    }

    private Button viewAllAddressBtn;

    public static final int MANAGE_ADDRESS=1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        viewAllAddressBtn =view.findViewById(R.id.view_all_address_Btn);

        viewAllAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myAddressIntent = new Intent(getContext(), MyAddressActivity.class);
                myAddressIntent.putExtra("MODE",MANAGE_ADDRESS);
                startActivity(myAddressIntent);

            }
        });

        return view;
    }
}
