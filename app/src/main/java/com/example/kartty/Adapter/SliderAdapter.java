package com.example.kartty.Adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.kartty.Model.Slider;
import com.example.kartty.R;

import java.util.List;


public class SliderAdapter extends PagerAdapter {

    private List<Slider> sliderlist;

    public SliderAdapter(List<Slider> sliderlist) {
        this.sliderlist = sliderlist;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout,container,false);
        ConstraintLayout bannerContainer = view.findViewById(R.id.bannerContainer);
        bannerContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderlist.get(position).getBackgroundColor())));
        ImageView banner = view .findViewById(R.id.banner_slider);

        Glide.with(container.getContext()).load(sliderlist.get(position).getBanner()).apply(new RequestOptions().placeholder(R.drawable.star)).into(banner);

      //  banner.setImageResource(sliderlist.get(position).getBanner());
        container.addView(view,0);

        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return sliderlist.size();
    }
}
