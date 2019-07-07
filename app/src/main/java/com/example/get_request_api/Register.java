package com.example.get_request_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Register extends AppCompatActivity {
    private EditText e1,e2,e3,e4,e5;
    private Button b1;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1 = findViewById(R.id.order_count);
        e2 = findViewById(R.id.profile_id);
        e3 = findViewById(R.id.food_id);
        e4 = findViewById(R.id.chef_id);
        e5 = findViewById(R.id.status);
        b1 = findViewById(R.id.post);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "{"+
                        "\"order_count\":" + "\"" + e1.getText().toString() + "\"," +
                        "\"profile_id\":" + "\"" + e2.getText().toString() + "\"," +
                        "\"food_id\":" + "\"" + e3.getText().toString() + "\"," +
                        "\"chef_key\":" + "\"" + e4.getText().toString() + "\"," +
                        "\"status\":" + "\"" + e5.getText().toString() + "\"" +
                        "}";
                Log.i("Jaydeep",data);
                Submit(data);
            }
        });
    }
    private  void Submit(String data){
        final String saveData =  data;
        String url = "http://192.168.0.101/cookkar/index.php";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.i("Jaydeep",response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    //Log.i("Jaydeep",response);
                    Toast.makeText(getApplicationContext(), object.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    //Log.i("Jaydeep",response);
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType(){ return  "application/json; charset=utf-8"; }

            @Override
            public byte[] getBody() throws AuthFailureError{
                try {
                    return saveData == null ? null : saveData.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    //e.printStackTrace();
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }
}
