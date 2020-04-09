package com.example.kartty.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kartty.Adapter.CategoryAdapter;
import com.example.kartty.Adapter.HomePageAdapter;
import com.example.kartty.Model.Category;
import com.example.kartty.Model.HomePageModel;
import com.example.kartty.Model.HorizontalProductModel;
import com.example.kartty.Model.Slider;
import com.example.kartty.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.kartty.Queries.DBqueries.categoryList;
import static com.example.kartty.Queries.DBqueries.firebaseFirestore;
import static com.example.kartty.Queries.DBqueries.homePageModelList;
import static com.example.kartty.Queries.DBqueries.loadCategories;
import static com.example.kartty.Queries.DBqueries.loadFragmentData;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private HomePageAdapter adapter;
    private RecyclerView homePageRecyclerView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        firebaseFirestore = FirebaseFirestore.getInstance();


        categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        if (categoryList.size() == 0){
            loadCategories(categoryAdapter,getContext());
        }else {
            categoryAdapter.notifyDataSetChanged();
        }

        homePageRecyclerView = view.findViewById(R.id.Home_page_recyclerView);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);

        adapter = new HomePageAdapter(homePageModelList);
        homePageRecyclerView.setAdapter(adapter);

        if (homePageModelList.size() == 0){
            loadFragmentData(adapter,getContext());
        }else {
            categoryAdapter.notifyDataSetChanged();
        }






        return view;
    }


}
