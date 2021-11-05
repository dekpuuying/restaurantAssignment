package com.order.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.order.myapplication.model.Restaurant;
import com.order.myapplication.model.RestaurantDetail;
import com.order.myapplication.repository.RestaurantService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    String web = "https://order-plz.herokuapp.com";

    ListView stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Restaurant");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(web)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestaurantService restaurantService = retrofit.create(RestaurantService.class);
        Call<Restaurant> restaurantCall = restaurantService.getRestaurants();
        List<RestaurantDetail> restaurantDetails = new ArrayList<>();
        try {
            Restaurant restaurant = restaurantCall.execute().body();
            restaurantDetails = restaurant.getRestaurants();
        } catch (Exception e) {
            System.out.println("Error : " + e.toString());
        }
        stores = (ListView) findViewById(R.id.Listview);

//      Start
        CustomAdapterRestaurant customAdapterRestaurant = new CustomAdapterRestaurant(MainActivity.this,restaurantDetails);
        stores.setAdapter(customAdapterRestaurant);
//      End

//        List<HashMap<String, String>> fill_data = new ArrayList<HashMap<String, String>>();
//        String[] from = new String[]{"name", "kind", "img"};
//        int[] to = new int[]{R.id.restaurantName, R.id.restaurantKind, R.id.restaurantImg};
//        for (int i = 0; i < restaurantDetails.size(); i++) {
//            HashMap<String, String> myM = new HashMap<String, String>();
//            myM.put("id", restaurantDetails.get(i).getId() + "");
//            myM.put("name", restaurantDetails.get(i).getName());
//            myM.put("kind", restaurantDetails.get(i).getKind());
//            myM.put("img", restaurantDetails.get(i).getImage_url());
//            fill_data.add(myM);
//        }
//        SimpleAdapter myA = new SimpleAdapter(stores.getContext(), fill_data, R.layout.storelist, from, to);
//        stores.setAdapter(myA);



        stores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Object item = adapterView.getItemAtPosition(i);
                    JSONObject selected = new JSONObject();
                    selected = new JSONObject(new Gson().toJson(item));
                    Intent intent = new Intent(MainActivity.this, RestaurantMenu.class);
                    intent.putExtra("id",selected.getInt("id"));
                    MainActivity.this.startActivity(intent);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });
    }

}
