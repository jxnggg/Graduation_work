package com.example.martapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Cart {
    String Cart_Product_ID;
    String Price;

    String Cart_Product_Name;


    public String getcart_ID() {
        return Cart_Product_ID;
    }

    public void setcart_ID(String Cart_Product_ID) {
        this.Cart_Product_ID = Cart_Product_ID;
    }

    public String getcart_name() {
        return Cart_Product_Name;
    }

    public void setcart_name(String Cart_Product_Name) {
        this.Cart_Product_Name = Cart_Product_Name;
    }

    public String getcart_price() {
        return Price;
    }

    public void setcart_price(String Price) {
        this.Price = Price;
    }





    public Cart(String Cart_Product_ID, String Cart_Product_Name, String Price) {
        this.Cart_Product_ID = Cart_Product_ID;
        this.Cart_Product_Name = Cart_Product_Name;
        this.Price = Price;

    }
}