package com.example.basicgetapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        EditText nik = findViewById(R.id.nik);

        findViewById(R.id.cari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nik_text = nik.getText().toString();
                if(TextUtils.isEmpty(nik_text)){
                    Toast.makeText(v.getContext(),"NIK Kosong",Toast.LENGTH_SHORT).show();
                }else{

                    Intent intent = new Intent(MainActivity.this, ActivityLain.class);
                    intent.putExtra("nik",nik_text);
                    startActivity(intent);

                }
            }
        });
    }
}