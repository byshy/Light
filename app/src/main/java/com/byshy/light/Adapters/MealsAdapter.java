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

import com.byshy.light.Activities.MealActivity;
import com.byshy.light.Models.Meal;
import com.byshy.light.R;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsViewHolder> {

    private ArrayList<Meal> mData;
    private Context mContext;

    public MealsAdapter(ArrayList<Meal> mData, Context context) {
        this.mData = mData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_meal_item, viewGroup, false);
        return new MealsAdapter.MealsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder mealsViewHolder, int i) {
        final Meal meal = mData.get(i);

        mealsViewHolder.mMealName.setText(meal.getName());
        mealsViewHolder.mRestaurant.setText(meal.getRestaurant());
        mealsViewHolder.mIcon.setImageResource(meal.getTypeIcon());
        mealsViewHolder.mPrice.setText(String.valueOf(meal.getPrice()));

        mealsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MealActivity.class);
                intent.putExtra("meal", meal);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MealsViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIcon;
        private TextView mMealName;
        private TextView mRestaurant;
        private TextView mPrice;

        public MealsViewHolder(@NonNull View itemView) {
            super(itemView);

            mIcon = itemView.findViewById(R.id.meal_item_icon);
            mMealName = itemView.findViewById(R.id.order_date_or_meal_name);
            mRestaurant = itemView.findViewById(R.id.order_meal_restaurant);
            mPrice = itemView.findViewById(R.id.order_total_price_or_meal_price);
        }

    }

}
