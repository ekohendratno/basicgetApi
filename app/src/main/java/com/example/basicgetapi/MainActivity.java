package com.example.basicgetapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * UBAH JADI INI
         */
        TextView nama = findViewById(R.id.nama);
        TextView alamat = findViewById(R.id.alamat);

        EditText nik = findViewById(R.id.nik);

        findViewById(R.id.cari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nik_text = nik.getText().toString();
                if(TextUtils.isEmpty(nik_text)){
                    Toast.makeText(v.getContext(),"NIK Kosong",Toast.LENGTH_SHORT).show();
                }else{

                    String URL = "http://192.168.100.1/webapi/api.php?nik="+nik_text;

                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
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
        });
    }
}