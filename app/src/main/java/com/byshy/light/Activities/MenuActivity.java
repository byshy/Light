package com.byshy.light.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.byshy.light.Adapters.MealsAdapter;
import com.byshy.light.Models.Meal;
import com.byshy.light.R;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.menu_recycler_view);

        Intent intent = getIntent();
        Bundle bundle;

        if (intent != null){
            bundle = intent.getExtras();
            if (bundle != null){
                meals = bundle.getParcelableArrayList("meals");
            }
        }

        MealsAdapter adapter = new MealsAdapter(meals, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
