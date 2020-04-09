package com.example.kartty.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kartty.Model.AddressesModel;
import com.example.kartty.R;

import java.util.List;

import static com.example.kartty.DeliveryActivity.SELECT_ADDRESS;
import static com.example.kartty.Fragment.MyAccountFragment.MANAGE_ADDRESS;
import static com.example.kartty.MyAddressActivity.refreshItem;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.Viewholder> {

    private List<AddressesModel> addressesModelList;

    private int MODE;
    private int preSelectedPosition = -1;


    public AddressesAdapter(List<AddressesModel> addressesModelList, int MODE) {
        this.addressesModelList = addressesModelList;
        this.MODE = MODE;
    }

    @NonNull
    @Override
    public AddressesAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ad,viewGroup,false);
       return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressesAdapter.Viewholder holder, int position) {

        String name = addressesModelList.get(position).getFullname();
        String address = addressesModelList.get(position).getAddress();
        String pincode = addressesModelList.get(position).getPincode();
        Boolean selected = addressesModelList.get(position).getSelected();



        holder.setData(name,address,pincode,selected,position);


    }

    @Override
    public int getItemCount()
    {
        return addressesModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView fullname;
        private TextView address;
        private TextView pincode;
        private ImageView icon;
        private LinearLayout optionContainer;


        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            fullname = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pincode);
            icon = itemView.findViewById(R.id.icon_view);
            optionContainer = itemView.findViewById(R.id.option_container);


        }

        private void setData(String username, String userAddress, String userPincode, Boolean selected, final int position){

            fullname.setText(username);
            address.setText(userAddress);
            pincode.setText(userPincode);

            if (MODE==SELECT_ADDRESS){
                icon.setImageResource(R.drawable.check);

                if (selected)
                {
                    icon.setVisibility(View.VISIBLE);
                    preSelectedPosition = position;
                }else
                {
                    icon.setVisibility(View.GONE);
                }
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (preSelectedPosition != position) {
                            addressesModelList.get(position).setSelected(true);
                            addressesModelList.get(preSelectedPosition).setSelected(false);
                            refreshItem(preSelectedPosition, position);
                            preSelectedPosition = position;
                        }

                    }
                });

            }else
                if (MODE==MANAGE_ADDRESS)
                {

                    optionContainer.setVisibility(View.GONE);
                    icon.setImageResource(R.drawable.ic_more);
                    icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            optionContainer.setVisibility(View.VISIBLE);
                            refreshItem(preSelectedPosition,preSelectedPosition);
                            preSelectedPosition=position;
                        }
                    });

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            refreshItem(preSelectedPosition,preSelectedPosition);
                            preSelectedPosition = -1;

                        }
                    });
                }

        }
    }
}
