package com.example.frequentflier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        EditText editText6 = findViewById(R.id.editText6);
        Spinner spinner3 = findViewById(R.id.spinner3);
        Button button7 = findViewById(R.id.button7);

        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
       // String dpid = "";
        ArrayList<String> list = new ArrayList<String>();

        RequestQueue queue = Volley.newRequestQueue(MainActivity6.this);
        String url = "http://10.0.2.2:8080/frequentflier/GetPassengerids.jsp?pid="+pid;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                String[] result = s.trim().split("#");

                for(int i=0;i<result.length;i++){
                    String output = result[i];
                    list.add(output);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity6.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner3.setAdapter(adapter);
            }
        },null);
        queue.add(request);

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String dpid = adapterView.getSelectedItem().toString();

                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String pc = editText6.getText().toString();
                        String url2 = "http://10.0.2.2:8080/frequentflier/TransferPoints.jsp?spid="+pid+"&dpid="+dpid+"&npoints="+pc;

                        StringRequest request2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                String result = s.trim();
                                if(result.contains("Transfer is successful")){

                                    Toast.makeText(MainActivity6.this,pc+" Points Transferred Successfully",Toast.LENGTH_LONG).show();

                                }
                                else{
                                    Toast.makeText(MainActivity6.this,"Points not updated",Toast.LENGTH_LONG).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        });
                        queue.add(request2);

                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}