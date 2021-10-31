package com.order.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetail {

    private int id;
    private String name;
    private String image_url;
    private String kind;
    private List<Menu> menus = new ArrayList<>();

    public RestaurantDetail() {
    }

    public RestaurantDetail(int id, String name, String image_url, String kind) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.kind = kind;
    }

    public RestaurantDetail(int id, String name, String image_url, String kind, List<Menu> menus) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.kind = kind;
        this.menus = menus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "retuarantDetail{" +
                "menus=" + menus +
                '}';
    }

}
