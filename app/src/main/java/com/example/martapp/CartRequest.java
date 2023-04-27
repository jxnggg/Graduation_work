package com.example.martapp;

import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CartRequest extends StringRequest {


    final static private String URL = "http://fnzmdpfak.dothome.co.kr/showcartsum.php";
    private Map<String, String> map;


    public CartRequest(String sum_Price, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("sum_Price",sum_Price);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}

