package com.order.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.order.myapplication.model.Menu;
import com.order.myapplication.model.RestaurantDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapterRestaurant extends BaseAdapter {

    private Context context;
    private List<RestaurantDetail> restaurantDetails;

    public CustomAdapterRestaurant(Context context, List<RestaurantDetail> restaurantDetails) {
        this.context = context;
        this.restaurantDetails = restaurantDetails;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return restaurantDetails.size();
    }
    @Override
    public Object getItem(int position) {
        return restaurantDetails.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CustomAdapterRestaurant.ListviewRestaurant holder;
        if (convertView == null) {
            holder = new CustomAdapterRestaurant.ListviewRestaurant(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.storelist, null, true);

            holder.imgRestaurant = (ImageView) convertView.findViewById(R.id.restaurantImg);
            holder.txtName = (TextView) convertView.findViewById(R.id.restaurantName);
            holder.txtKind = (TextView) convertView.findViewById(R.id.restaurantKind);
            convertView.setTag(holder);
        }else {
            holder = (CustomAdapterRestaurant.ListviewRestaurant) convertView.getTag();
        }

        holder.txtName.setText(restaurantDetails.get(position).getName());
        holder.txtKind.setText(restaurantDetails.get(position).getKind());
        Picasso.get().load(restaurantDetails.get(position).getImage_url()).into(holder.imgRestaurant);
        return convertView;
    }

    private class ListviewRestaurant {

        private ImageView imgRestaurant;

        protected TextView txtName;

        protected TextView txtKind;

    }


}
