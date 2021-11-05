package com.order.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.order.myapplication.model.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterMenu extends BaseAdapter {

    private Context context;
    private List<Menu> menus;

    public CustomAdapterMenu(Context context, List<Menu> menus) {
        this.context = context;
        this.menus = menus;
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
        return menus.size();
    }
    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListviewMenu holder;
        if (convertView == null) {
            holder = new ListviewMenu(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menulist, null, true);

            holder.imgMenu = (ImageView) convertView.findViewById(R.id.imgMenu);
            holder.txtName = (TextView) convertView.findViewById(R.id.txtMenuName);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.txtMenuPrice);
            convertView.setTag(holder);
        }else {
            holder = (ListviewMenu) convertView.getTag();
        }

        holder.txtName.setText(menus.get(position).getName());
        holder.txtPrice.setText(menus.get(position).getPrice().intValue() + " THB");
        Picasso.get().load(menus.get(position).getImage_url()).into(holder.imgMenu);
        return convertView;
    }

    private class ListviewMenu {

        private ImageView imgMenu;

        protected TextView txtName;

        protected TextView txtPrice;

    }

}
