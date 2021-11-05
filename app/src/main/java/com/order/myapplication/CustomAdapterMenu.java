package com.order.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.order.myapplication.model.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterMenu extends BaseAdapter {

    private Context context;
    private List<Menu> menus;

    private int sum = 0;

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
            holder.cbMenu = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        }else {
            holder = (ListviewMenu) convertView.getTag();
        }

        holder.txtName.setText(menus.get(position).getName());
        holder.txtPrice.setText(menus.get(position).getPrice().intValue() + " THB");
        Picasso.get().load(menus.get(position).getImage_url()).into(holder.imgMenu);
        holder.cbMenu.setChecked(menus.get(position).getChecked());
        holder.cbMenu.setText(menus.get(position).getPrice().intValue()+"");


        holder.cbMenu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    if(b)
                    {
                        sum = sum + Integer.parseInt (compoundButton.getText().toString());
                    } else
                    {
                        sum = sum - Integer.parseInt (compoundButton.getText().toString());
                    }
                    if(sum>0)
                    {
                        Toast.makeText(context, "Total price : "+sum+" THB", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e)
                {
                    System.out.println("Error : " + e.toString());
                }
            }
        });


        return convertView;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    private class ListviewMenu {

        ImageView imgMenu;

        TextView txtName;

        TextView txtPrice;

        CheckBox cbMenu;

    }

}
