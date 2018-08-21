package com.noseapp.noseapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.noseapp.noseapp.Local.LocalActivity;

public class Welcome extends AppCompatActivity {

    private final int splash = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final TextView ed_welcome = (TextView) findViewById(R.id.ed_welcome);

         /*if (user == usuario de local){
            Intent intent = new Intent(Welcome.this, LoginActivity.class);
            startActivity(intent);
        }else if (user == usuario de cliente){
            Intent intent = new Intent(Welcome.this, LoginActivity.class);
            startActivity(intent);
        }*/
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(Welcome.this, LocalActivity.class);
                startActivity(intent);
            }
        }, splash);
    }
}
