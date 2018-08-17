package com.noseapp.noseapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

public class InicioActivity extends AppCompatActivity {

    private final int splash = 3000;
    private EditText ed_name, ed_email, ed_password, ed2_password;
    private Button btn_signup;
    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(InicioActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, splash);
    }
}
