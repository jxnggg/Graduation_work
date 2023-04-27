package com.example.martapp;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class QRRequest extends StringRequest {


    final static private String URL = "http://fnzmdpfak.dothome.co.kr/qrresult.php";
    private Map<String, String> map;


    public QRRequest(String Cart_Product_ID, String Price, String Cart_Product_Name, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("Cart_Product_ID",Cart_Product_ID);//key와 value 쌍으로 이루어진 데이터 보관
        map.put("Price", Price);
        map.put("Cart_Product_Name", Cart_Product_Name);
}

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

