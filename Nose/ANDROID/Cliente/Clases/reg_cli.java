package com.sam.nose.Cliente;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.sam.nose.LoginActivity;
import com.sam.nose.R;

import org.json.JSONException;
import org.json.JSONObject;

public class reg_cli extends AppCompatActivity {
    private EditText txt_nombresCli, txt_apellidosCli, txt_cedulaCli, txt_emailCli,
            txt_direccionCli, txt_tlf, txt_userCli, txt_passwordCli;
    private Button btn_regCli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_cli);

        txt_nombresCli = (EditText) findViewById(R.id.txt_nombresCli);
        txt_apellidosCli = (EditText) findViewById(R.id.txt_apellidosCli);
        txt_cedulaCli = (EditText) findViewById(R.id.txt_cedulaCli);
        txt_emailCli = (EditText) findViewById(R.id.txt_correoCli);
        txt_direccionCli = (EditText) findViewById(R.id.txt_direccionCli);
        txt_tlf = (EditText) findViewById(R.id.txt_tlf);
        txt_userCli = (EditText) findViewById(R.id.txt_userCli);
        txt_passwordCli = (EditText) findViewById(R.id.txt_passwordCli);

        Button btn_regCli = (Button) findViewById(R.id.btn_regCli);
       btn_regCli.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder builder = new AlertDialog.Builder(reg_cli.this);
               builder.setMessage("error");
           }
       });

    }
}