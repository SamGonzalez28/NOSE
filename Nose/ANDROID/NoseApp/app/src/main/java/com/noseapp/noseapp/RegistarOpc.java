package com.noseapp.noseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.noseapp.noseapp.Cliente.RegistrarCliente;
import com.noseapp.noseapp.Local.RegistrarLocal;

public class RegistarOpc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_opc);

        ImageButton btn_newLocal = (ImageButton) findViewById(R.id.btn_newLocal);
        ImageButton btn_newCl = (ImageButton) findViewById(R.id.btn_newCli);

        btn_newLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistarOpc.this, RegistrarLocal.class);
                startActivity(intent);
            }
        });

        btn_newCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistarOpc.this, RegistrarCliente.class);
                startActivity(intent);
            }
        });
    }
}