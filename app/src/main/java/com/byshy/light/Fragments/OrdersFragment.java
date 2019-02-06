package com.byshy.light.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byshy.light.Adapters.OrdersAdapter;
import com.byshy.light.Models.Meal;
import com.byshy.light.Models.Order;
import com.byshy.light.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = root.findViewById(R.id.orders_recycler_view);

        ArrayList<Meal> meals = new ArrayList<>();
        meals.add(new Meal("meal1newmeal1newmeal1newmeal1new", "image",1.0, "res", "entrees", 60, "desc"));
        meals.add(new Meal("meal2new", "image",2.2, "res", "meal", 20, "desc"));
        meals.add(new Meal("meal3new", "image",4.5, "res", "salads", 50, "desc"));
        meals.add(new Meal("meal4new", "image",4.1, "res", "drink", 30, "desc"));
        meals.add(new Meal("meal5new", "image",2.7, "res", "desert", 40, "desc"));

        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(meals, "today today"));
        orders.add(new Order(meals, "today today"));
        orders.add(new Order(meals, "today today"));
        orders.add(new Order(meals, "today today"));

        OrdersAdapter adapter = new OrdersAdapter(orders, getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        return root;
    }

}
