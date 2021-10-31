package com.order.myapplication.model;

public class Menu {

    private int id;
    private int restaurant_id;
    private String name;
    private String image_url;
    private Float price;
    private int deliver_time;

    public Menu() {
    }

    public Menu(int id, int restaurant_id, String name, String image_url, Float price, int deliver_time) {
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.name = name;
        this.image_url = image_url;
        this.price = price;
        this.deliver_time = deliver_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getDeliver_time() {
        return deliver_time;
    }

    public void setDeliver_time(int deliver_time) {
        this.deliver_time = deliver_time;
    }

    @Override
    public String toString() {
        return "menu{" +
                "id=" + id +
                ", restaurant_id=" + restaurant_id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", price=" + price +
                ", deliver_time=" + deliver_time +
                '}';
    }
}
