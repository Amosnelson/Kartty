package com.example.kartty.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.kartty.Model.HorizontalProductModel;
import com.example.kartty.ProductDetailsActivity;
import com.example.kartty.R;

import java.util.List;

public class GridProductViewAdapter extends BaseAdapter {

    List<HorizontalProductModel> horizontalProductModelList;

    public GridProductViewAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    //    public List<HorizontalProductModel> getHorizontalProductModelList()
//    {
//        return horizontalProductModelList;
//    }
//
//    public void setHorizontalProductModelList(List<HorizontalProductModel> horizontalProductModelList) {
//        this.horizontalProductModelList = horizontalProductModelList;
//    }

    @Override
    public int getCount()
    {
        return horizontalProductModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item, null);
        view.setElevation(0);
        view.setBackgroundColor(Color.parseColor("#ffffff"));

        if (convertView == null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetailsIntent = new Intent(parent.getContext(), ProductDetailsActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage = view.findViewById(R.id.horizontal_productImage);
            TextView productTitle = view.findViewById(R.id.horizontal_productTitle);
            TextView productDescription = view.findViewById(R.id.horizontal_productDescription);
            TextView productPrice = view.findViewById(R.id.horizontal_productPrice);

            Glide.with(parent.getContext()).load(horizontalProductModelList.get(position).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.star)).into(productImage);
            productTitle.setText(horizontalProductModelList.get(position).getProductTitle());
            productDescription.setText(horizontalProductModelList.get(position).getProductDescription());
            productPrice.setText(horizontalProductModelList.get(position).getProductPrice());

        } else {
            view=convertView;

        }

        return view;
    }
}
