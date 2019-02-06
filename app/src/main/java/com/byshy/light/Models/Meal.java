package com.byshy.light.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.byshy.light.R;

public class Meal implements Parcelable {

    private String mName;
    private String mImage;
    private double mRating;
    private String mRestaurant;
    private String mType;
    private int mTypeIcon;
    private int mPrice;
    private String mDescription;

    public Meal(String name, String image, double rating, String restaurant, String type, int price, String description) {
        this.mName = name;
        this.mImage = image;
        this.mRating = rating;
        this.mRestaurant = restaurant;
        this.mType = type;
        this.mPrice = price;
        this.mDescription = description;
        setTypeIcon(type);
    }

    protected Meal(Parcel in) {
        mName = in.readString();
        mImage = in.readString();
        mRating = in.readDouble();
        mRestaurant = in.readString();
        mType = in.readString();
        mTypeIcon = in.readInt();
        mPrice = in.readInt();
        mDescription = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }

    public double getRating() {
        return mRating;
    }

    public void setRating(int mRating) {
        this.mRating = mRating;
    }

    public String getRestaurant() {
        return mRestaurant;
    }

    public void setRestaurant(String mRestaurant) {
        this.mRestaurant = mRestaurant;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public int getTypeIcon() {
        return mTypeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        if (typeIcon.equals("entrees")) {
            this.mTypeIcon = R.drawable.ic_account_balance_wallet_black_24dp;
        } else if (typeIcon.equals("meal")) {
            this.mTypeIcon = R.drawable.ic_home_black_24dp;
        } else if (typeIcon.equals("salads")) {
            this.mTypeIcon = R.drawable.ic_chrome_reader_mode_black_24dp;
        } else if (typeIcon.equals("drink")) {
            this.mTypeIcon = R.drawable.ic_info_outline_black_24dp;
        } else if (typeIcon.equals("desert")) {
            this.mTypeIcon = R.drawable.ic_settings_black_24dp;
        }
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mImage);
        dest.writeDouble(mRating);
        dest.writeString(mRestaurant);
        dest.writeString(mType);
        dest.writeInt(mTypeIcon);
        dest.writeInt(mPrice);
        dest.writeString(mDescription);
    }
}
