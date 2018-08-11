package com.sam.nose.Local;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sam.nose.R;

public class localActivity extends AppCompatActivity {

    private ImageButton clientes;
    private ImageButton menu;
    private ImageButton registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        clientes = (ImageButton) findViewById(R.id.btn_clientes);
        menu = (ImageButton) findViewById(R.id.btn_menus);
        registros = (ImageButton) findViewById(R.id.btn_registros);

        oyentes();
    }

    private void oyentes(){

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               irMenu();
            }
        });
    }

    private void irMenu(){
        Intent intent = new Intent(getApplicationContext(), MenusActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
