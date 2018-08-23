package com.noseapp.noseapp.Local;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.noseapp.noseapp.LectorQR;
import com.noseapp.noseapp.Menu.OpcMenuLocal;
import com.noseapp.noseapp.R;

public class LocalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        ImageButton menu = (ImageButton) findViewById(R.id.btn_menus);
        //ImageButton registros = (ImageButton) findViewById(R.id.btn_registros);
        ImageButton  clientes = (ImageButton) findViewById(R.id.btn_clientes);
        ImageButton qr = (ImageButton) findViewById(R.id.btn_qr);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalActivity.this, OpcMenuLocal.class);
                startActivity(intent);
            }
        });

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ListarClientesDeLocal
            }
        });

       /* registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalActivity.this, ListarRegistros.class);
                startActivity(intent);
            }
        });*/

       qr.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent= new Intent(LocalActivity.this, GenerarQr.class);
               startActivity(intent);
           }
       });
    }

}
