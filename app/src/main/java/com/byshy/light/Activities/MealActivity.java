package com.byshy.light.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.byshy.light.Models.Meal;
import com.byshy.light.R;

public class MealActivity extends AppCompatActivity {

    private TextView mMealName;
    private TextView mMealRestaurant;
    private TextView mMealPrice;
    private TextView mMealDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        mMealName = findViewById(R.id.meal_name);
        mMealRestaurant = findViewById(R.id.meal_restaurant);
        mMealPrice = findViewById(R.id.meal_price);
        mMealDescription = findViewById(R.id.meal_description);

        Meal meal;

        Intent intent = getIntent();

        if (intent != null){
            Bundle bundle = intent.getExtras();
            meal = bundle.getParcelable("meal");
            if (meal != null){
                mMealName.setText(meal.getName());
                mMealRestaurant.setText(meal.getRestaurant());
                mMealPrice.setText(String.valueOf(meal.getPrice()));
                mMealDescription.setText(meal.getDescription());
            }
        }

    }
}
