package com.noseapp.noseapp.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.noseapp.noseapp.R;

public class OpcMenuLocal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opc_menu_local);


        ImageButton btn_newMenu = (ImageButton) findViewById(R.id.btn_newMenu);
        ImageButton btn_listarMenu = (ImageButton) findViewById(R.id.btn_listarMenu);

        btn_newMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpcMenuLocal.this, RegistrarMenu.class);
                startActivity(intent);
            }
        });

        btn_listarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpcMenuLocal.this, Listar_menuActivity.class);
                startActivity(intent);
            }
        });
    }
}
