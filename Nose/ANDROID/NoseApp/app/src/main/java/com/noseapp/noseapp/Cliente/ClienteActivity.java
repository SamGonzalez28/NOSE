package com.noseapp.noseapp.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.noseapp.noseapp.LectorQR;
import com.noseapp.noseapp.R;

public class ClienteActivity extends AppCompatActivity {

    ImageButton btn_leerQr, btn_editCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        btn_leerQr = (ImageButton) findViewById(R.id.btn_leerQr);
        btn_editCli = (ImageButton) findViewById(R.id.btn_Editclientes);

        btn_leerQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, LectorQR.class);
            }
        });
        btn_editCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_leerQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClienteActivity.this, LectorQR.class);
                startActivity(intent);
            }
        });
    }
}
