package com.example.martapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

public class CartListAdapter extends BaseAdapter {

    private Context context;
    private List<Cart> cartList;

    public CartListAdapter(Context context, List<Cart> cartList){
        this.context = context;
        this.cartList = cartList;
    }

    //출력할 총갯수를 설정하는 메소드

    @Override
    public int getCount() {
        return cartList.size();
    }

    //특정한 유저를 반환하는 메소드
    @Override
    public Object getItem(int i) {
        return cartList.get(i);
    }

    //아이템별 아이디를 반환하는 메소드
    @Override
    public long getItemId(int i) {
        return i;
    }

    //가장 중요한 부분
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.activity_cart, null);

        //뷰에 다음 컴포넌트들을 연결시켜줌
        TextView Cart_Product_ID = (TextView)v.findViewById(R.id.Cart_Product_ID);
        TextView Price = (TextView)v.findViewById(R.id.Price);
        TextView Cart_Product_Name = (TextView)v.findViewById(R.id.Cart_Product_Name);


        Cart_Product_ID.setText(cartList.get(i).getcart_ID());
        Price.setText(cartList.get(i).getcart_price());
        Cart_Product_Name.setText(cartList.get(i).getcart_name());




        //만든뷰를 반환함
        return v;
    }
}