package com.example.kartty.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kartty.Adapter.ProductSpecificationAdapter;
import com.example.kartty.Model.ProductSpecification;
import com.example.kartty.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSpecificationFragment extends Fragment {

    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    private RecyclerView productSpecificationRecyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_product_specification, container, false);
        productSpecificationRecyclerView =  view.findViewById(R.id.product_specicfication_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productSpecificationRecyclerView.setLayoutManager(linearLayoutManager);

        List<ProductSpecification>productSpecificationList = new ArrayList<>();
        productSpecificationList.add(new ProductSpecification(0, "General"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(0,"Display" ));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(0, "General"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(0,"Display" ));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));
        productSpecificationList.add(new ProductSpecification(1, "RAM","4gb"));

        ProductSpecificationAdapter productSpecificationAdapter = new ProductSpecificationAdapter(productSpecificationList);
        productSpecificationRecyclerView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();
        return  view;
    }
}
