 package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        TextView textView18 = findViewById(R.id.textView18);
        TextView textView21 = findViewById(R.id.textView21);
        Spinner spinner2 = findViewById(R.id.spinner2);

        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        ArrayList<String> list = new ArrayList<String>();

        RequestQueue queue = Volley.newRequestQueue(MainActivity5.this);
        String url ="http://10.0.2.2:8080/frequentflier/AwardIds.jsp?pid="+pid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                String[] result = s.trim().split("#");

                for(int i=0;i<result.length;i++){
                    String output = result[i];
                    list.add(output);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity5.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter);
            }
        },null);
        queue.add(request);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String awardid = adapterView.getSelectedItem().toString();

                String url2 = "http://10.0.2.2:8080/frequentflier/RedemptionDetails.jsp?awardid="+awardid+"&pid="+pid;
                StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        String[] result = s.trim().split("#");
                        for(int i=0;i<result.length;i++) {
                            String[] output = result[i].split(",");
                            textView18.setText("Price Desc."+"\n"+"\n"+output[0]+"\n"+"\n"+"Points Needed "+"\n"+"\n"+output[1]);
                            textView21.setText(output[2]+"                "+output[3]);

                        }

                    }
                },null);
                queue.add(request2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}