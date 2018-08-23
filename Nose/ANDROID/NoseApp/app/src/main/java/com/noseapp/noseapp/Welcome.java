package com.noseapp.noseapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.Response;
import com.noseapp.noseapp.Cliente.ClienteActivity;
import com.noseapp.noseapp.Local.LocalActivity;
import com.noseapp.noseapp.WS.ModelosJson.ClienteJson;

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
                    Intent intent = new Intent(Welcome.this, LocalActivity.class);
                    startActivity(intent);
                }
            }, splash);

        }else if (InicioActivity.TIPO == "C"){
            ed_welcome.setText(InicioActivity.NOMBRE_WELCOME);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(Welcome.this, ClienteActivity.class);
                    startActivity(intent);
                }
            }, splash);
        }
    }
}
