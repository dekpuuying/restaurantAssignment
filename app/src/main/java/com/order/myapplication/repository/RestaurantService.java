package com.order.myapplication.repository;

import com.order.myapplication.model.Restaurant;
import com.order.myapplication.model.RestaurantDetail;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantService {

    @GET("/restaurants.json")
    Call<Restaurant> getRestaurants();

    @GET("/restaurants/{id}.json")
    Call<RestaurantDetail> getRestaurantDetail(@Path("id") int id);


}
