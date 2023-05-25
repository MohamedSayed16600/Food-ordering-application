package com.example.wagba.models;

public class FavoritesModel {
    private String Status;
    private String OrderID;
    private String TotalPrice;
    private String Gate;
    private String time;
    private String date;

    public FavoritesModel(String status, String orderID, String totalPrice, String gate, String time, String date) {
        Status = status;
        OrderID = orderID;
        TotalPrice = totalPrice;
        Gate = gate;
        this.time = time;
        this.date = date;
    }

    public FavoritesModel() {

    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getGate() {
        return Gate;
    }

    public void setGate(String gate) {
        Gate = gate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
