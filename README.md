# basicgetApi

basicgetApi adalah program yang dibuat guna melakukkan operasi get data ke server local maupun online dengan memanfaatkan library volley

beberapa hal perlu diperhatikan:

#AndroidManifest.xml
 
<uses-permission android:name="android.permission.INTERNET" />

#build.gradle

implementation 'com.android.volley:volley:1.1.1'


#activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:padding="16dp"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/nik"
        android:hint="Masukkan NIK"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
    <Button
        android:id="@+id/cari"
        android:text="Cari"
        android:layout_marginTop="16dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    <TextView
        android:id="@+id/keluaran"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</LinearLayout>


#MainActivity.java


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView keluaran = findViewById(R.id.keluaran);
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
                            keluaran.setText(response.toString());
                            Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
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


#api.php

<pre>
<?php

$nik = $_GET['nik'];

//silahkah di kreasi di hubungkan ke database mysql

$data = array();
if($nik == "1111"){

    $data['response'] = true;
    $data['nama'] = "eko";
    $data['alamat'] = "lampung";


}else{
    $data['response'] = false;
    

}

header('Content-Type: application/json');
echo json_encode($data);

</pre>

