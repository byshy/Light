package com.byshy.light.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.byshy.light.Activities.MenuActivity;
import com.byshy.light.Models.Order;
import com.byshy.light.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private ArrayList<Order> mData;
    private Context mContext;

    public OrdersAdapter(ArrayList<Order> mData, Context context) {
        this.mData = mData;
        mContext = context;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_meal_item, viewGroup, false);
        return new OrdersAdapter.OrdersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder ordersViewHolder, int i) {
        final Order order = mData.get(i);

        ordersViewHolder.mIcon.setVisibility(View.GONE);
        ordersViewHolder.mDateTime.setText(order.getDateTime());
        ordersViewHolder.mRestaurant.setText(order.getRestaurant());
        ordersViewHolder.mTotalPrice.setText(String.valueOf(order.getTotalPrice()));

        ordersViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(mContext, MenuActivity.class);
                menuIntent.putExtra("meals", order.getMeals());
                mContext.startActivity(menuIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class OrdersViewHolder extends RecyclerView.ViewHolder{

        private ImageView mIcon;
        private TextView mDateTime;
        private TextView mRestaurant;
        private TextView mTotalPrice;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.meal_item_icon);
            mDateTime = itemView.findViewById(R.id.order_date_or_meal_name);
            mRestaurant= itemView.findViewById(R.id.order_meal_restaurant);
            mTotalPrice = itemView.findViewById(R.id.order_total_price_or_meal_price);
        }

    }

}
