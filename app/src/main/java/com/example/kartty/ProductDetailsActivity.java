package com.example.kartty;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kartty.Adapter.MyRewardsAdapter;
import com.example.kartty.Adapter.ProductDetailsAdapter;
import com.example.kartty.Adapter.ProductImagesAdapter;
import com.example.kartty.Model.RewardModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productimageViewPager;
    private TabLayout viewPagerIndicator;
    private FloatingActionButton addToWishlistBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTablayout;

    private Button coupenRedeemBtn;

    ////coupendialog

    public static TextView coupenTitle;
    public static TextView coupenExpiryDate;
    public static TextView coupenBody;

    private static RecyclerView coupenRecyclerView;
    private static LinearLayout selectedCoupen;




    ////coupendialog

    ////Rating

    private LinearLayout rateNowContainer;

    private Button buyNowBtn;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        productimageViewPager = findViewById(R.id.product_image_viewPager);
        viewPagerIndicator = findViewById(R.id.viewPager_indicator);
        addToWishlistBtn = findViewById(R.id.add_to_wishlist_btn);

        productDetailsViewPager = findViewById(R.id.product_details_viewPager);

        productDetailsTablayout = findViewById(R.id.product_details_tablayout);

        buyNowBtn = findViewById(R.id.buy_now_btn);

        coupenRedeemBtn = findViewById(R.id.coupen_redemtion_btn);



        List<Integer> productImage = new ArrayList<>();
        productImage.add(R.drawable.iphone);
        productImage.add(R.drawable.summer);
        productImage.add(R.drawable.summer2);
        productImage.add(R.drawable.glasses);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImage);
        productimageViewPager.setAdapter(productImagesAdapter);

        viewPagerIndicator.setupWithViewPager(productimageViewPager, true);

        addToWishlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishlistBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));

                } else {

                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishlistBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }

            }
        });

        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTablayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTablayout));
        productDetailsTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        rateNowContainer = findViewById(R.id.rate_now_container);
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    setRating(starPosition);
                }
            });
        }

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deliveryIntent = new Intent(ProductDetailsActivity.this, DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });

        ///coupen dialog

        final Dialog checkCoupenPriceDialog = new Dialog(ProductDetailsActivity.this);
        checkCoupenPriceDialog.setContentView(R.layout.coupen_redeem_dialog);
        checkCoupenPriceDialog.setCancelable(true);
        checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toggleRecyclerView = checkCoupenPriceDialog.findViewById(R.id.toggle_recyclerView);
        coupenRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupens_recyclerView);
        selectedCoupen = checkCoupenPriceDialog.findViewById(R.id.selected_coupen);

        coupenTitle = checkCoupenPriceDialog.findViewById(R.id.coupenTitle);
        coupenExpiryDate = checkCoupenPriceDialog.findViewById(R.id.coupen_validity);
        coupenBody = checkCoupenPriceDialog.findViewById(R.id.coupen_body);


        TextView originalPrice = checkCoupenPriceDialog.findViewById(R.id.original_price);
        TextView discountPrice = checkCoupenPriceDialog.findViewById(R.id.discounted_price);


        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coupenRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel>rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback","till 2nd,june 2020","GET 20% CASHBACK on any product above $200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Discount","till 2nd,june 2020","GET 20% CASHBACK on any product above $200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free","till 2nd,june 2020","GET 20% CASHBACK on any product above $200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Cashback","till 2nd,june 2020","GET 20% CASHBACK on any product above $200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Discount","till 2nd,june 2020","GET 20% CASHBACK on any product above $200/- and below $3000/-"));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free","till 2nd,june 2020","GET 20% CASHBACK on any product above $200/- and below $3000/-"));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList,true);
        coupenRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();


        toggleRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogRecyclerView();

            }
        });

        ////coupen dialog


        coupenRedeemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkCoupenPriceDialog.show();

            }
        });


    }

    public static void showDialogRecyclerView(){

        if (coupenRecyclerView.getVisibility() == View.GONE){
            coupenRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupen.setVisibility(View.GONE);

        }else {

            coupenRecyclerView.setVisibility(View.GONE);
            selectedCoupen.setVisibility(View.VISIBLE);

        }

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setRating(int starPosition) {

        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            ImageView starBtn = (ImageView) rateNowContainer.getChildAt(x);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if (x <= starPosition) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_item, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();

            return true;

        } else if (id == R.id.main_search_icon) {

            return true;
        } else if (id == R.id.main_cartItem) {

            MainActivity.showCart= true;
            Intent cartIntent = new Intent(ProductDetailsActivity.this, MainActivity.class);
            startActivity(cartIntent);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
