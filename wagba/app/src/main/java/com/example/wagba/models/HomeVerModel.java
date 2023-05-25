package com.example.wagba.models;

public class HomeVerModel {

    String imageurl;
    String name;
    String price;
    String timing;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public HomeVerModel(String imageurl, String name, String price, String timing) {
        this.imageurl = imageurl;
        this.name = name;
        this.price = price;
        this.timing = timing;
    }

    public HomeVerModel(){

    }


}
