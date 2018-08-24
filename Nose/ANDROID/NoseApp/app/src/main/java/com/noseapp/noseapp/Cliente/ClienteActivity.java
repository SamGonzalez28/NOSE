package com.noseapp.noseapp.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.noseapp.noseapp.LectorQR;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.Registros.Listar_registros_cliente;
import com.noseapp.noseapp.WS.ModelosJson.ClienteJson;

public class ClienteActivity extends AppCompatActivity {

    ImageButton btn_leerQr, btn_editCli, btn_regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        btn_leerQr = (ImageButton) findViewById(R.id.btn_leerQr);
        btn_editCli = (ImageButton) findViewById(R.id.btn_Editclientes);
        btn_regis = (ImageButton) findViewById(R.id.btn_listaregistros);


        btn_editCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, perfil_cliente_Activity.class);
                startActivity(intent);
            }
        });

        btn_leerQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, LectorQR.class);
                startActivity(intent);
            }
        });

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, Listar_registros_cliente.class);
                startActivity(intent);
            }
        });
    }
}
