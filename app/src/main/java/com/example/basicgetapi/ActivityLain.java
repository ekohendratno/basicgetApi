package com.example.basicgetapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityLain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lain);

        Intent intent = getIntent();
        String nik_text = intent.getStringExtra("nik");

        TextView nama = findViewById(R.id.nama);
        TextView alamat = findViewById(R.id.alamat);

        String URL = "http://192.168.100.1/webapi/api.php?nik="+nik_text;

        RequestQueue queue = Volley.newRequestQueue(ActivityLain.this);
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                /**
                 * UBAH JADI INI
                 */
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.getBoolean("response")){ //jika response TRUE

                        nama.setText( jsonObject.getString("nama") );
                        alamat.setText( jsonObject.getString("alamat") );

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }
        });
        queue.add(request);
    }
}
