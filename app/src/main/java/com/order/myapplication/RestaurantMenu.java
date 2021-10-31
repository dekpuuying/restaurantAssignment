package com.order.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.order.myapplication.model.Menu;
import com.order.myapplication.model.RestaurantDetail;
import com.order.myapplication.repository.RestaurantService;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantMenu extends AppCompatActivity {

    String web = "https://order-plz.herokuapp.com";

    ListView listViewMenus;
    TextView restaurantName, restaurantKind;
    ImageView restaurantImg;
    //    CheckBox selectedMenu;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storedetail);
        int id = getIntent().getExtras().getInt("id");

        restaurantName = (TextView) findViewById(R.id.txtRestaurantName);
        restaurantKind = (TextView) findViewById(R.id.txtRestaurantKind);
        restaurantImg = (ImageView) findViewById(R.id.imgRestaurant);
        listViewMenus = (ListView) findViewById(R.id.ListViewMenu);

        sum = 0;

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

        try {
            System.out.println(menus.size());
            List<HashMap<String, String>> fill_data = new ArrayList<HashMap<String, String>>();
            String[] from = new String[]{"name", "price", "img"};
            int[] to = new int[]{R.id.txtMenuName, R.id.txtMenuPrice, R.id.imgMenu};
            for (int i = 0; i < menus.size(); i++) {
                HashMap<String, String> myM = new HashMap<String, String>();
                myM.put("id", menus.get(i).getId() + "");
                myM.put("name", menus.get(i).getName());
                myM.put("price", menus.get(i).getPrice().intValue() + " THB");
                myM.put("img", menus.get(i).getImage_url());
                fill_data.add(myM);
            }

            SimpleAdapter myA = new SimpleAdapter(listViewMenus.getContext(), fill_data, R.layout.menulist, from, to);
            listViewMenus.setAdapter(myA);
        } catch (Exception e) {
            System.out.println("Error : " + e.toString());
        }




        listViewMenus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Object item = adapterView.getItemAtPosition(i);
                    JSONObject selected = new JSONObject();
                    selected = new JSONObject(new Gson().toJson(item));
                    sum += selected.getInt("price");
                    Toast.makeText(getApplicationContext(), "Total Price \n" + sum + " THB", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });

    }
}

