package com.noseapp.noseapp.Cliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.Login.LoginActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.ClienteJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.HashMap;
/**
 * Esta actividad permite registrar un cliente
 */
public class RegistrarCliente extends AppCompatActivity {

    private EditText txt_nombresCli, txt_apellidosCli, txt_cedulaCli, txt_emailCli,
            txt_direccionCli, txt_tlf, txt_userCli, txt_passwordCli;
    private Button btn_regCli;
    RequestQueue requestQueue;

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
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btn_regCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyente();
                btn_regCli.setEnabled(false);
            }
        });
    }
    public void oyente() {
        String name = this.txt_nombresCli.getText().toString().trim();
        String lastname = this.txt_apellidosCli.getText().toString().trim();
        String cedula = this.txt_cedulaCli.getText().toString().trim();
        String email = this.txt_emailCli.getText().toString().trim();
        String direccion = this.txt_direccionCli.getText().toString().trim();
        String tlf = this.txt_tlf.getText().toString().trim();
        String user = this.txt_userCli.getText().toString().trim();
        String pass = this.txt_passwordCli.getText().toString().trim();

        if (name.trim().isEmpty() || lastname.trim().isEmpty() || cedula.trim().isEmpty()
                || email.trim().isEmpty() || direccion.trim().isEmpty() || tlf.trim().isEmpty()
                || user.trim().isEmpty() || pass.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.reg, Toast.LENGTH_LONG).show();
            return;
        } else {

            HashMap<String, String> mp = new HashMap<>();
            mp.put("nombres", name);
            mp.put("apellidos", lastname);
            mp.put("ci", cedula);
            mp.put("correo", email);
            mp.put("direccion", direccion);
            mp.put("telefono", tlf);
            mp.put("user", user);
            mp.put("clave", pass);
            VolleyPeticion<ClienteJson> registrarCliente = Conexion.registroCliente(
                    getApplicationContext(),
                    mp,
                    new Response.Listener<ClienteJson>() {
                        @Override
                        public void onResponse(ClienteJson response) {
                            Toast.makeText(RegistrarCliente.this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrarCliente.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegistrarCliente.this, "NO SE REGISTRO", Toast.LENGTH_SHORT).show();
                            btn_regCli.setEnabled(true);
                        }
                    }
            );
            requestQueue.add(registrarCliente);
        }
    }
}
