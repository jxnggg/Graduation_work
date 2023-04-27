package com.example.martapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {


    final static private String URL = "http://fnzmdpfak.dothome.co.kr/selectSearch.php";

    private Map<String, String> map;

    public SearchRequest(String Product_name,String Price, String Product_info,String Location,String Size,String Volume,Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("Product_name", Product_name);
        map.put("Price", Price);
        map.put("Product_info", Product_info);
        map.put("Location", Location);
        map.put("Size", Size);
        map.put("Volume", Volume);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
