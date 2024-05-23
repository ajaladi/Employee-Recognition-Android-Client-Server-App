package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Spinner spinner = findViewById(R.id.spinner);
        TextView textView13 = findViewById(R.id.textView13);
        TextView textView14 = findViewById(R.id.textView14);
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        ArrayList<String> list = new ArrayList<String>();

        RequestQueue queue = Volley.newRequestQueue(MainActivity4.this);
        String url = "http://10.0.2.2:8080/frequentflier/Flights.jsp?pid="+pid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                String[] result = s.trim().split("#");

                for(int i=0;i<result.length;i++){
                    String[] output = result[i].split(",");

                    list.add(output[0]);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity4.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

            }
        },null);
        queue.add(request);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String fid = adapterView.getSelectedItem().toString();

                String url2 = "http://10.0.2.2:8080/frequentflier/FlightDetails.jsp?flightid="+fid;
                StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
//                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String s) {

                        String[] result = s.trim().split("#");
                        for(int i=0;i<result.length;i++) {
                            String[] output = result[i].split(",");
                            textView13.setText("Departure:"+output[0]+"\n"+"Arrival: "+output[1]+"\n"+"Miles: "+output[2]);
                            textView14.setText(output[3]+"                "+output[4]);

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
