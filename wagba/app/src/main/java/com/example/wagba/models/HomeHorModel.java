package com.example.wagba.models;

import androidx.cardview.widget.CardView;

public class HomeHorModel {

    String imageurl;
    String name;
    CardView cardView;

    public HomeHorModel(String imageurl, String name,CardView cardView) {
        this.imageurl = imageurl;
        this.name = name;
        this.cardView = cardView;
    }

    public HomeHorModel(){

    }

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
    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }
}
