package com.noseapp.noseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.noseapp.noseapp.Cliente.RegistrarCliente;
import com.noseapp.noseapp.Local.RegistrarLocal;

/**
 * Clase Usada para que el usuario elija la opcion de registrarse como
 * Local o como Ciente
 */
public class RegistarOpc extends AppCompatActivity {
    /**
     * Metodo que se ejecuta al crearse la actividad
     * Presenta la opcion de registrandose como
     * un local o un cliente
     * redirige hacia las actividades
     * RegistrarLocal y Registrar Cliente
     * @param savedInstanceState
     */
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
