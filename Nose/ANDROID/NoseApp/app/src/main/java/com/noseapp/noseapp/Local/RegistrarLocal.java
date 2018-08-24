package com.noseapp.noseapp.Local;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.Login.LoginActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarLocal extends AppCompatActivity {

    TextView nombre, ruc, domicilio, telefono, password;
    private Button btn_regLocal;
    private ProgressBar barra;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_local);

        nombre = (TextView) findViewById(R.id.ed_nombreLocal);
        ruc = (TextView) findViewById(R.id.ruc);
        domicilio = (TextView) findViewById(R.id.direccionLocal);
        telefono = (TextView) findViewById(R.id.tlfLocal);
        password = (TextView) findViewById(R.id.passwordLocal);
        barra = (ProgressBar) findViewById(R.id.loadingRegLocal);

        btn_regLocal = (Button) findViewById(R.id.btn_regLocal);
        requestQueue = Volley.newRequestQueue(getApplicationContext());


        btn_regLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyente();
            }
        });
    }

    public void oyente() {
        String name = this.nombre.getText().toString().trim();
        String RUC = this.ruc.getText().toString().trim();
        String Direccion = this.domicilio.getText().toString().trim();
        String tlf = this.telefono.getText().toString().trim();
        String pass = this.password.getText().toString().trim();

        HashMap<String, String> mp = new HashMap<>();
        mp.put("nombre", name);
        mp.put("ruc", RUC);
        mp.put("direccion", Direccion);
        mp.put("telefono", tlf);
        mp.put("clave", pass);
        VolleyPeticion<LocalJson> registrarLocal = Conexion.registroLocal(
                getApplicationContext(),
                mp,
                new Response.Listener<LocalJson>() {
                    @Override
                    public void onResponse(LocalJson response) {
                        Toast.makeText(RegistrarLocal.this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrarLocal.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistrarLocal.this, "NO SE REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(registrarLocal);
    }
}
