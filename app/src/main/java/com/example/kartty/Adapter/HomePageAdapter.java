package com.example.kartty.Adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.kartty.Model.HomePageModel;
import com.example.kartty.Model.HorizontalProductModel;
import com.example.kartty.Model.Slider;
import com.example.kartty.ProductDetailsActivity;
import com.example.kartty.R;
import com.example.kartty.ViewAllActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {


    private List<HomePageModel> homePageModelList;
    private RecyclerView.RecycledViewPool recycledViewPool;


    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    public class BannerSliderViewholder extends RecyclerView.ViewHolder {

        private ViewPager bannerSliderViewPager;
        private List<Slider> sliderList;
        private int currentPage;
        private Timer timer;
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
       private List<Slider>arrengeList;

        public BannerSliderViewholder(@NonNull View itemView) {
            super(itemView);
            bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);


        }


        public void setBannerSliderViewPager(final List<Slider> sliderList) {

            currentPage=2;
            if(timer!=null){

                timer.cancel();
            }

            arrengeList = new ArrayList<>();
            for (int x=0;x<sliderList.size();x++)
            {
                arrengeList.add(x,sliderList.get(x));
            }

            arrengeList.add(0,sliderList.get(sliderList.size() -2));
            arrengeList.add(1,sliderList.get(sliderList.size() -1));
           arrengeList.add(sliderList.get(0));
           arrengeList.add(sliderList.get(1));



       SliderAdapter sliderAdapter = new SliderAdapter(arrengeList);
            bannerSliderViewPager.setAdapter(sliderAdapter);
            bannerSliderViewPager.setClipToPadding(false);
            bannerSliderViewPager.setPageMargin(20);

            bannerSliderViewPager.setCurrentItem(currentPage);

            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    currentPage = position;

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(arrengeList);
                    }

                }
            };
            bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
            startBannerSlideShow(arrengeList);

            bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    pageLooper(arrengeList);
                    stopBannerSlideShow();

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(arrengeList);
                    }
                    return false;
                }
            });
        }

        private void pageLooper(List<Slider> sliderList) {

            if (currentPage == sliderList.size() - 2) {
                currentPage = 2;
                bannerSliderViewPager.setCurrentItem(currentPage, false);

            }

            if (currentPage == 1) {
                currentPage = sliderList.size() - 3;
                bannerSliderViewPager.setCurrentItem(currentPage, false);

            }

        }

        private void startBannerSlideShow(final List<Slider> sliderList) {
            final Handler handler = new Handler();
            final Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentPage >= sliderList.size()) {
                        currentPage = 1;
                    }
                    bannerSliderViewPager.setCurrentItem(currentPage++, true);

                }
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSlideShow() {

            timer.cancel();

        }
    }

    public class StripSliderViewholder extends RecyclerView.ViewHolder {
        private ImageView stripAdImage;
        private ConstraintLayout stripAdContainer;


        public StripSliderViewholder(@NonNull View itemView) {
            super(itemView);

            stripAdImage = itemView.findViewById(R.id.strip_ad_image);
            stripAdContainer = itemView.findViewById(R.id.strip_ad_container);
        }

        private void setStripAd(String resource, String color) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.star)).into(stripAdImage);
            stripAdContainer.setBackgroundColor(Color.parseColor(color));

        }
    }

    public class HorizontalSliderViewholder extends RecyclerView.ViewHolder {

        private ConstraintLayout container;
        private TextView layoutTitle;
        private Button viewAllBtn;
        private RecyclerView horizontalRecyclerView;

        public HorizontalSliderViewholder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            layoutTitle = itemView.findViewById(R.id.horizontal_text_title);
            viewAllBtn = itemView.findViewById(R.id.horizontal_view_btn);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontal_recycler_view);
            horizontalRecyclerView.setRecycledViewPool(recycledViewPool);
        }

        private void setHorizontalProductLayout(List<HorizontalProductModel> horizontalProductModelList, String title, String color) {

            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
            layoutTitle.setText(title);
            if (horizontalProductModelList.size() > 4) {
                viewAllBtn.setVisibility(View.VISIBLE);
                viewAllBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent viewAllIntents = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntents.putExtra("layout_code", 0);
                        itemView.getContext().startActivity(viewAllIntents);

                    }
                });
            } else {
                viewAllBtn.setVisibility(View.INVISIBLE);
            }
            HorizontalProductAdapter horizontalProductAdapter = new HorizontalProductAdapter(horizontalProductModelList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalRecyclerView.setLayoutManager(linearLayoutManager);

            horizontalRecyclerView.setAdapter(horizontalProductAdapter);
            horizontalProductAdapter.notifyDataSetChanged();
        }
    }

    public class GridProductViewholder extends RecyclerView.ViewHolder {

        private TextView gridLayoutTitle;
        private Button gridLayoutBtn;
        private GridLayout gridProductLayout;
        private ConstraintLayout container;



        public GridProductViewholder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            gridLayoutTitle = itemView.findViewById(R.id.grid_layout_title);
            gridLayoutBtn = itemView.findViewById(R.id.grid_layout_btn);
            gridProductLayout = itemView.findViewById(R.id.grid_layout);

        }

        private void setGridProductLayout(List<HorizontalProductModel> horizontalProductModelList, String title, String color) {

            container.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));

            gridLayoutTitle.setText(title);
            for (int x=0;x<4;x++){
                ImageView productImage = gridProductLayout.getChildAt(x).findViewById(R.id.horizontal_productImage);
                TextView productTitle = gridProductLayout.getChildAt(x).findViewById(R.id.horizontal_productTitle);
                TextView productDescription = gridProductLayout.getChildAt(x).findViewById(R.id.horizontal_productDescription);
                TextView productPrice = gridProductLayout.getChildAt(x).findViewById(R.id.horizontal_productPrice);

               Glide.with(itemView.getContext()).load(horizontalProductModelList.get(x).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.star)).into(productImage);

              // productImage.setImageResource(horizontalProductModelList.get(x).getProductImage());
                productTitle.setText(horizontalProductModelList.get(x).getProductTitle());
                productDescription.setText(horizontalProductModelList.get(x).getProductDescription());
                    productPrice.setText(horizontalProductModelList.get(x).getProductPrice());

                gridProductLayout.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });

            }


            gridLayoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent viewAllIntents = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntents.putExtra("layout_code", 1);
                    itemView.getContext().startActivity(viewAllIntents);

                }
            });

        }
    }


    @Override
    public int getItemViewType(int position) {

        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;

            case 1:
                return HomePageModel.STRIP_AD_BANNER;

            case 2:

                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;

            case 3:

                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View bannerview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sliding_ad_layout, viewGroup, false);
                return new BannerSliderViewholder(bannerview);
            case HomePageModel.STRIP_AD_BANNER:

                View stripview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.strip_ad_layout, viewGroup, false);
                return new StripSliderViewholder(stripview);

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:

                View horizontalview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_layout, viewGroup, false);
                return new HorizontalSliderViewholder(horizontalview);

            case HomePageModel.GRID_PRODUCT_VIEW:

                View Gridview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gridlayout, viewGroup, false);
                return new GridProductViewholder(Gridview);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:
                List<Slider> sliderList = homePageModelList.get(position).getSliderList();
                ((BannerSliderViewholder) holder).setBannerSliderViewPager(sliderList);
                break;


            case HomePageModel.STRIP_AD_BANNER:
                String resource = homePageModelList.get(position).getResource();
                String color = homePageModelList.get(position).getBackgroundColor();
                ((StripSliderViewholder) holder).setStripAd(resource, color);
                break;


            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:

                String layoutcolor = homePageModelList.get(position).getBackgroundColor();
                String horizontaltitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductModel> horizontalProductModelList = homePageModelList.get(position).getHorizontalProductModelList();
                ((HorizontalSliderViewholder) holder).setHorizontalProductLayout(horizontalProductModelList, horizontaltitle, layoutcolor);

                break;

            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridLayoutcolor =  homePageModelList.get(position).getBackgroundColor();
                String gridLayouttitle = homePageModelList.get(position).getTitle();
                List<HorizontalProductModel> gridProductModelList = homePageModelList.get(position).getHorizontalProductModelList();
                ((GridProductViewholder) holder).setGridProductLayout(gridProductModelList, gridLayouttitle, gridLayoutcolor);

            default:

                return;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }
}
