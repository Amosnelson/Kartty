package com.example.kartty.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kartty.Model.CartItem;
import com.example.kartty.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItem> cartItemList;

    public CartAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemList.get(position).getType()) {
            case 0:
                return CartItem.CART_ITEM;
            case 1:
                return CartItem.TOTAL_AMOUNT;
            default:
                return -1;
        }


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case CartItem.CART_ITEM:
                View cartItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item, viewGroup, false);
                return new CartItemViewholder(cartItemView);

            case CartItem.TOTAL_AMOUNT:

                View cartTotalView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_total_amount_layout, viewGroup, false);
                return new CartTotalAmountViewholder(cartTotalView);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (cartItemList.get(position).getType()) {
            case CartItem.CART_ITEM:
                int resource = cartItemList.get(position).getProductImage();
                String title = cartItemList.get(position).getProductTitle();
                int freeCoupens = cartItemList.get(position).getFreeCoupens();
                String productPrice = cartItemList.get(position).getProductPrice();
                String cuttedPrice = cartItemList.get(position).getCuttedPrice();
                int offersApplied = cartItemList.get(position).getOffersApplied();
                ((CartItemViewholder)holder).setItemDetails(resource,title,freeCoupens,productPrice,cuttedPrice,offersApplied);

                break;
            case CartItem.TOTAL_AMOUNT:

                String totalItems = cartItemList.get(position).getTotalItems();
                String totalItemPrice = cartItemList.get(position).getTotalItemPrice();
                String deliveryPrice = cartItemList.get(position).getDeliveryPrice();
                String totalAmount = cartItemList.get(position).getTotalAmount();
                String savedAmount = cartItemList.get(position).getSavedAmount();

                ((CartTotalAmountViewholder)holder).setTotalAmount(totalItems,totalItemPrice,deliveryPrice,totalAmount,savedAmount);
                break;
            default:
                return;


        }
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    class CartItemViewholder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private ImageView freeCoupenIcon;
        private TextView productTitle;
        private TextView freeCoupens;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView offersApplied;
        private TextView coupensApplied;
        private TextView productQuantity;


        public CartItemViewholder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupenIcon = itemView.findViewById(R.id.free_coupen_icon);
            freeCoupens = itemView.findViewById(R.id.tv_free_coupens);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cuttedPrice);
            offersApplied = itemView.findViewById(R.id.offers_Aplied);
            coupensApplied = itemView.findViewById(R.id.coupen_applied);
            productQuantity = itemView.findViewById(R.id.product_quantity);

        }

        private void setItemDetails(int resource, String title, int freeCoupensNo, String productPriceText, String cuttedPriceText, int offersAppliedNo) {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCoupensNo > 0) {
                freeCoupenIcon.setVisibility(View.VISIBLE);
                freeCoupens.setVisibility(View.VISIBLE);
                if (freeCoupensNo == 1) {
                    freeCoupens.setText("free" + freeCoupensNo + "coupen");
                } else {
                    freeCoupens.setText("free" + freeCoupensNo + "coupen");
                }
            } else {
                freeCoupenIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);

            }
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);

            if (offersAppliedNo > 0) {
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNo + "offers applied");
            } else {
                offersApplied.setVisibility(View.INVISIBLE);
            }


        }
    }

    class CartTotalAmountViewholder extends RecyclerView.ViewHolder {

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;

        public CartTotalAmountViewholder(@NonNull View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id.total_item);
            totalItemPrice = itemView.findViewById(R.id.total_item_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id.total_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);

        }

        private void setTotalAmount(String totalItemText, String totalItemPriceText, String deliveryPriceText, String totalAmountText, String savedAmountText) {

            totalItems.setText(totalItemText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(deliveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);


        }
    }


}
