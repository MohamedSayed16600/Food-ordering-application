package com.example.wagba.models;

public class Cartmodel {
    String image;
    String nameCart;
    String priceCart;

    public Cartmodel(String image, String nameCart, String priceCart) {
        this.image = image;
        this.nameCart = nameCart;
        this.priceCart = priceCart;
    }
    public Cartmodel (){

    }




    public String getImageCart() {
        return image;
    }

    public void setImageCart(String image) {
        this.image = image;
    }

    public String getNameCart() {
        return nameCart;
    }

    public void setNameCart(String nameCart) {
        this.nameCart = nameCart;
    }

    public String getPriceCart() {
        return priceCart;
    }

    public void setPriceCart(String priceCart) {
        this.priceCart = priceCart;
    }
}
