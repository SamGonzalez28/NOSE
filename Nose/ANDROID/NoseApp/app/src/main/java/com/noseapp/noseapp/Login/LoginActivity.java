package com.noseapp.noseapp.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.noseapp.noseapp.LectorQR;
import com.noseapp.noseapp.Local.LocalActivity;
import com.noseapp.noseapp.Menu.OpcMenuLocal;
import com.noseapp.noseapp.RegistarOpc;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.Welcome;

import org.json.JSONArray;

public class LoginActivity extends AppCompatActivity {
    EditText ed_password, ed_user;
    JSONArray ja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView txt_reg = (TextView) findViewById(R.id.txt_reg);
        Button btn_log = (Button) findViewById(R.id.btnlog);
        ed_user = (EditText) findViewById(R.id.ed_user);
        ed_password = (EditText) findViewById(R.id.ed_password);

        final TextView et1 = (TextView) findViewById(R.id.ed_nombreLocal);

        txt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistarOpc.class);
                startActivity(intent);

            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Welcome.class);
                startActivity(intent);

            }
        });
    }
}