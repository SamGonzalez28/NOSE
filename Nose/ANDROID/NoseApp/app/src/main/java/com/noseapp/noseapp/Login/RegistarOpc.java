package com.noseapp.noseapp.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.noseapp.noseapp.Cliente.RegistrarCliente;
import com.noseapp.noseapp.Local.RegistrarLocal;
import com.noseapp.noseapp.R;

public class RegistarOpc extends AppCompatActivity {

    /**
     * Esta actividad permite seleccionar de que manera se registrara un usuario
     * siendo llamada de Login Activity
     * redirigiendo a RegistrarLocal, y RegistrarCliente respectivamente
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
                finish();
            }
        });

        btn_newCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistarOpc.this, RegistrarCliente.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
