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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.CollationElementIterator;

public class QRresult extends AppCompatActivity {

    private Button ButtonScan,ButtonCart,btn_home2;
    private TextView pro_id, pro_name, pro_price;
    private IntentIntegrator qrscan; //qr스캔 사용하기 위한

    @Override
    protected void onCreate(Bundle savedInstanceState) {//화면에 출력
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrresult);

        ButtonScan = (Button) findViewById(R.id.ButtonScan);
        ButtonCart = (Button) findViewById(R.id.ButtonCart);
        btn_home2 = (Button) findViewById(R.id.btn_home2);
        pro_id = (TextView) findViewById(R.id.pro_id);
        pro_name = (TextView) findViewById(R.id.pro_name);
        pro_price = (TextView)  findViewById(R.id.pro_price);


        qrscan = new IntentIntegrator(this);

        btn_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QRresult.this, MainActivity.class);
                startActivity(intent); //액티비티 이동

            }
        });

        ButtonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//scan 버튼 눌렀을 시
                //scan option
                qrscan.setPrompt("Scanning");
                //qrScan.setOrientationLocked(false);
                qrscan.initiateScan();
            }
        });

        ButtonCart = findViewById(R.id.ButtonCart);
        ButtonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Cart_Product_ID = pro_id.getText().toString();//get은 key에 해당하는 value 값 return
                String Price = pro_price.getText().toString();
                String Cart_Product_Name = pro_name.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 상품등록에 성공
                                Toast.makeText(getApplicationContext(),"상품 등록 성공.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(QRresult.this, QRresult.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(),"상품 등록 실패.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                QRRequest qrRequest = new QRRequest(Cart_Product_ID,Price,Cart_Product_Name, responseListener);
                RequestQueue queue = Volley.newRequestQueue(QRresult.this);
                queue.add(qrRequest);

            }
        });

    }
    @Override//scan 결과값
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        //String Cart_Product_ID ;
        //String Price ;
        //String Cart_Product_Name ;

        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QRresult.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(QRresult.this, "스캔완료!", Toast.LENGTH_SHORT).show();

                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    pro_id.setText(obj.getString("Cart_Product_ID"));
                    pro_price.setText(obj.getString("Price"));
                    pro_name.setText(obj.getString("Cart_Product_Name"));

                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                    CollationElementIterator textViewResult = null;
                    textViewResult.setText(result.getContents());
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }




}