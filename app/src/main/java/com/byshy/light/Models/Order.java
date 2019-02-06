package com.byshy.light.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Order implements Parcelable {

    /**
     * it's expected to use the first item from the list to get
     * the name of the restaurant in future use cases (Adapters)
    */
    private ArrayList<Meal> mMeals;
    private String mDateTime;
    private int mTotalPrice;

    public Order() {
        mMeals = new ArrayList<>();
    }

    public Order(ArrayList<Meal> meals, String dateTime) {
        mMeals = meals;
        mDateTime = dateTime;
        getTotalPrice(meals);
    }

    protected Order(Parcel in) {
        this();
        in.readTypedList(mMeals, Meal.CREATOR);
        mDateTime = in.readString();
        mTotalPrice = in.readInt();
    }

    public ArrayList<Meal> getMeals() {
        return mMeals;
    }

    public String getRestaurant() {
        return mMeals.get(0).getRestaurant();
    }

    public void setMeals(ArrayList<Meal> mMeals) {
        this.mMeals = mMeals;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public void setDateTime(String mDateTime) {
        this.mDateTime = mDateTime;
    }

    private int getTotalPrice(ArrayList<Meal> meals) {
        int total = 0;

        for (int i = 0; i < meals.size(); i++) {
            total += meals.get(i).getPrice();
        }
        mTotalPrice = total;
        return total;
    }

    public int getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(int price) {
        this.mTotalPrice = price;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mMeals);
        dest.writeString(mDateTime);
        dest.writeInt(mTotalPrice);
    }

}
