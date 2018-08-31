package com.noseapp.noseapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.noseapp.noseapp.Cliente.ClienteActivity;
import com.noseapp.noseapp.Cliente.Cliente_Lateral;
import com.noseapp.noseapp.Local.Admin_Lateral;

public class Welcome extends AppCompatActivity {

    private final int splash = 2000;

    private TextView ed_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ed_welcome = (TextView) findViewById(R.id.ed_welcome);

        if (InicioActivity.TIPO == "L"){
            ed_welcome.setText(InicioActivity.NOMBRE_WELCOME);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(Welcome.this, Admin_Lateral.class);
                    startActivity(intent);
                    finish();
                }
            }, splash);

        }else if (InicioActivity.TIPO == "C"){
            ed_welcome.setText(InicioActivity.NOMBRE_WELCOME);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(Welcome.this, Cliente_Lateral.class);
                    startActivity(intent);
                    finish();
                }
            }, splash);
        }
    }
}
