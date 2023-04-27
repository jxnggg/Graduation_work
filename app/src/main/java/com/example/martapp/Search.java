package com.example.martapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.CollationElementIterator;

public class Search extends AppCompatActivity {

    private TextView tv_proname,tv_price,tv_detail,tv_location,tv_size,tv_vol;
    private EditText et_proname;
    private Button btn_search1,btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        tv_proname = findViewById(R.id.tv_proname);
        tv_price = findViewById(R.id.tv_price);
        tv_detail = findViewById(R.id.tv_detail);
        tv_size = findViewById(R.id.tv_size);
        tv_vol = findViewById(R.id.tv_vol);
        tv_location = findViewById(R.id.tv_location);
        btn_search1 = findViewById(R.id.btn_search1);
        et_proname = findViewById(R.id.et_proname);
        btn_home = findViewById(R.id.btn_home);

        btn_home=findViewById(R.id.btn_home);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Search.this, MainActivity.class);
                startActivity(intent); //액티비티 이동

            }
        });

        btn_search1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String Product_name = et_proname.getText().toString();
                String Price = tv_price.getText().toString();
                String Product_info = tv_detail.getText().toString();
                String Size = tv_size.getText().toString();
                String Volume = tv_vol.getText().toString();
                String Location = tv_location.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {//상품조회에 성공한 경우

                                String Product_name = jsonObject.getString("Product_name");
                                String Price=jsonObject.getString("Price");
                                String Product_info =jsonObject.getString("Product_info");
                                String Location =jsonObject.getString("Location");
                                String Size =jsonObject.getString("Size");
                                String Volume =jsonObject.getString("Volume");

                                Toast.makeText(getApplicationContext(), "상품조회에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Search.this, Search.class);

                                intent.putExtra("Product_name", Product_name); // 값 넣어줌
                                intent.putExtra("Price",Price);
                                intent.putExtra("Product_info", Product_info);
                                intent.putExtra("Size", Size);
                                intent.putExtra("Volume", Volume);
                                intent.putExtra("Location", Location);

                                startActivity(intent);


                            } else {//상품조회에 실패한 경우
                                Toast.makeText(getApplicationContext(), "상품조회에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                SearchRequest searchRequest = new SearchRequest(Product_name,Price,Product_info,Location,Size,Volume,responseListener);
                RequestQueue queue = Volley.newRequestQueue(Search.this);
                queue.add(searchRequest);

            }

        });

        Intent intent = getIntent();
        String Product_name = intent.getStringExtra("Product_name");
        String Price = intent.getStringExtra("Price");
        String Product_info = intent.getStringExtra("Product_info");
        String Size = intent.getStringExtra("Size");
        String Volume = intent.getStringExtra("Volume");
        String Location = intent.getStringExtra("Location"); // 값 받아 오는 코드


        tv_proname.setText(Product_name);
        tv_price.setText(Price);
        tv_detail.setText(Product_info);
        tv_size.setText(Size);
        tv_vol.setText(Volume);
        tv_location.setText(Location); //화면에 출력하는 코드

    }

}