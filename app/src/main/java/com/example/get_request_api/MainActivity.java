package com.example.get_request_api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    TextView t1;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView)findViewById(R.id.textview);
        b1 = findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
        b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);

            }
        });
    }
    private void jsonParse(){
        String url = "http://192.168.0.101/cookkar/index.php?profile_key=123";
        //Log.i("jaydeep",url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("jaydeep", response.toString());
                        try {
                            String message = response.getString("message");
//                            Log.i("cookkar", response.getString("status"));
//                            JSONArray profile = response.getJSONArray("items");
//                            for (int i =0 ; i<profile.length(); i++){
//                                JSONObject user_profile = profile.getJSONObject(i);
//
//                                String name = user_profile.getString("name");
//                                t1.append(name);
//                            }
                            t1.append(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    Log.i("jaydeep", new String(error.networkResponse.data));
                }catch(NullPointerException e){
                    e.printStackTrace();
                }
            }
        }

        );
        requestQueue.add(request);
    }

}
