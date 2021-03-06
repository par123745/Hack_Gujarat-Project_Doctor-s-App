package com.isha.doctors_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class login extends AppCompatActivity {
   EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1=findViewById(R.id.editText);
        t2=findViewById(R.id.editText2);

    }

    public void reg(View view) {

        Intent i = new Intent(login.this,MainActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        final String email=t1.getText().toString();
        final String password=t2.getText().toString();

        RequestQueue rq= Volley.newRequestQueue(login.this);
        String url="http://malnirisha.in.net/hospital/doc_login.php?email="+email+"&password="+password;
        StringRequest sr=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("1")){
                    Toast.makeText(login.this, "doc_login Success", Toast.LENGTH_SHORT).show();
                  Intent i = new Intent(login.this,Appointment.class);
                  i.putExtra("email",email);
                    startActivity(i);
                }
                if(response.trim().equals("0")){
                    Toast.makeText(login.this, "Email or password did not match", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
        sr.setShouldCache(false);
        sr.setRetryPolicy(new DefaultRetryPolicy(20*1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.add(sr);
    }
}
