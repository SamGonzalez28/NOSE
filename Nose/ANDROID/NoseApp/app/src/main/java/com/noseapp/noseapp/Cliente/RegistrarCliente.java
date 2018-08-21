package com.noseapp.noseapp.Cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.noseapp.noseapp.R;

public class RegistrarCliente extends AppCompatActivity {

    private EditText txt_nombresCli, txt_apellidosCli, txt_cedulaCli, txt_emailCli,
            txt_direccionCli, txt_tlf, txt_userCli, txt_passwordCli;
    private Button btn_regCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        txt_nombresCli = (EditText) findViewById(R.id.txt_nombresCli);
        txt_apellidosCli = (EditText) findViewById(R.id.txt_apellidosCli);
        txt_cedulaCli = (EditText) findViewById(R.id.txt_cedulaCli);
        txt_emailCli = (EditText) findViewById(R.id.txt_correoCli);
        txt_direccionCli = (EditText) findViewById(R.id.txt_direccionCli);
        txt_tlf = (EditText) findViewById(R.id.txt_tlfCli);
        txt_userCli = (EditText) findViewById(R.id.txt_userCli);
        txt_passwordCli = (EditText) findViewById(R.id.txt_passwordCli);

        btn_regCli = (Button) findViewById(R.id.btn_regCliente);
        btn_regCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AQUI SE DEBE ENVIAR TODOS LOS TXT AL HACER CLICK
            }
        });
    }
}
