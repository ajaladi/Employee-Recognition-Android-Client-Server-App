package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView10 = findViewById(R.id.textView10);

        Intent intent = getIntent();

        String pid = intent.getStringExtra("pid");

        RequestQueue queue = Volley.newRequestQueue(MainActivity3.this);
        String url = "http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+pid;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String[] result = s.trim().split("#");

                for(int i=0;i<result.length;i++){
                    String[] output = result[i].split(",");

                    textView10.setText(output[0]+"             "+output[1]+"            "+output[2]);

                }

            }
        },null);
        queue.add(request);
    }
}