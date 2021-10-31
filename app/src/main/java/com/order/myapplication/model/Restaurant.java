package com.order.myapplication.model;

import java.util.ArrayList;

public class Restaurant {

    private ArrayList<RestaurantDetail> restaurants;

    public Restaurant() {
    }

    public Restaurant(ArrayList<RestaurantDetail> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<RestaurantDetail> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<RestaurantDetail> restaurants) {
        this.restaurants = restaurants;
    }
}
