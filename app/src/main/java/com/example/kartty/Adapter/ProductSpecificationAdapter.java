package com.example.kartty.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kartty.Model.ProductSpecification;
import com.example.kartty.R;

import java.lang.reflect.Type;
import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {
    private List<ProductSpecification>productSpecificationList;

    public ProductSpecificationAdapter(List<ProductSpecification> productSpecificationList) {

        this.productSpecificationList = productSpecificationList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (productSpecificationList.get(position).getType())
        {

            case 0:
                return ProductSpecification.SPECIFICATION_TITLE;
            case 1:
                return ProductSpecification.SPECIFICATION_BODY;

            default:return -1;

        }
    }

    @NonNull
    @Override
    public ProductSpecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case ProductSpecification.SPECIFICATION_TITLE:
                TextView title =new TextView(viewGroup.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16,viewGroup.getContext()),setDp(16,viewGroup.getContext()),
                        setDp(16,viewGroup.getContext()),
                        setDp(16,viewGroup.getContext()));
                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);

            case ProductSpecification.SPECIFICATION_BODY:
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_specification_item,viewGroup,false);
                return new ViewHolder(view);
            default:return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationAdapter.ViewHolder holder, int position) {

        switch (productSpecificationList.get(position).getType()){
            case ProductSpecification.SPECIFICATION_TITLE:
                holder.setTitle(productSpecificationList.get(position).getTitle());

                break;

                case ProductSpecification.SPECIFICATION_BODY:
                    String featureTitle = productSpecificationList.get(position).getFeatureName();
                    String featureDetail = productSpecificationList.get(position).getFeatureValue();
                    holder.setFeature(featureTitle,featureDetail);
                    break;

            default:return;
        }


    }

    @Override
    public int getItemCount() {
        return productSpecificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView featureName;
        private TextView featureValue;
        private TextView title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }

        private void setTitle(String titleText){
            title = (TextView) itemView;
            title.setText(titleText);
        }

        private void setFeature(String featureTitle,String featuredetail){

            featureName = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);
            featureName.setText(featureTitle);
            featureValue.setText(featuredetail);
        }
    }

    private int setDp(int dp, Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
}
