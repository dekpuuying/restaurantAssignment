package com.order.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.order.myapplication.model.Menu;
import com.order.myapplication.model.RestaurantDetail;
import com.order.myapplication.repository.RestaurantService;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantMenu extends AppCompatActivity {

    String web = "https://order-plz.herokuapp.com";

    ListView listViewMenus;
    TextView restaurantName, restaurantKind;
    ImageView restaurantImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storedetail);
        int id = getIntent().getExtras().getInt("id");

        restaurantName = (TextView) findViewById(R.id.txtRestaurantName);
        restaurantKind = (TextView) findViewById(R.id.txtRestaurantKind);
        restaurantImg = (ImageView) findViewById(R.id.imgRestaurant);
        listViewMenus = (ListView) findViewById(R.id.ListViewMenu);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(web)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestaurantService restaurantService = retrofit.create(RestaurantService.class);
        Call<RestaurantDetail> restaurantCall = restaurantService.getRestaurantDetail(id);
        List<Menu> menus = new ArrayList<>();
        RestaurantDetail restaurant = new RestaurantDetail();
        try {
            restaurant = restaurantCall.execute().body();
            menus = restaurant.getMenus();
        } catch (Exception e) {
            System.out.println("Error : " + e.toString());
        }

        restaurantName.setText(restaurant.getName());
        restaurantKind.setText(restaurant.getKind());
        Picasso.get().load(restaurant.getImage_url()).into(restaurantImg);
//      Start

        CustomAdapterMenu customAdapterMenu = new CustomAdapterMenu(RestaurantMenu.this, menus);
        listViewMenus.setAdapter(customAdapterMenu);

//      End

//            List<HashMap<String, String>> fill_data = new ArrayList<HashMap<String, String>>();
//            String[] from = new String[]{"name", "price", "img"};
//            int[] to = new int[]{R.id.txtMenuName, R.id.txtMenuPrice, R.id.imgMenu};
//            for (int i = 0; i < menus.size(); i++) {
//                HashMap<String, String> myM = new HashMap<String, String>();
//                myM.put("id", menus.get(i).getId() + "");
//                myM.put("name", menus.get(i).getName());
//                myM.put("price", menus.get(i).getPrice().intValue() + " THB");
//                myM.put("img", menus.get(i).getImage_url());
//                fill_data.add(myM);
//            }
//
//            SimpleAdapter myA = new SimpleAdapter(listViewMenus.getContext(), fill_data, R.layout.menulist, from, to);
//            listViewMenus.setAdapter(myA);


    }

}

