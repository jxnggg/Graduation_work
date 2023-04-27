package com.example.martapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CartSend extends AppCompatActivity {

    private TextView sum_Price2;
    private Button cart_look,btn_send,btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_send);
        sum_Price2 = findViewById(R.id.sum_Price2);
        cart_look = findViewById(R.id.cart_look);


        cart_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sum_Price = sum_Price2.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {//상품조회에 성공한 경우

                                String sum_Price = jsonObject.getString("sum_Price");

                                Toast.makeText(getApplicationContext(), "장바구니 정보 조회에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CartSend.this, CartSend.class);

                                intent.putExtra("sum_Price", sum_Price+"원"); // 값 넣어줌

                                startActivity(intent);


                            } else {//상품조회에 실패한 경우
                                Toast.makeText(getApplicationContext(), "장바구니조회에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                CartRequest cartRequest = new CartRequest(sum_Price, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CartSend.this);
                queue.add(cartRequest);




            }


        });


        btn_send=findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartSend.this, Send.class);
                startActivity(intent); //액티비티 이동

            }
        });

        Intent intent = getIntent();
        String sum_Price = intent.getStringExtra("sum_Price");

        sum_Price2.setText(sum_Price);
    }



}