package com.noseapp.noseapp.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.RegistarOpc;
import com.noseapp.noseapp.WS.ModelosJson.ClienteJson;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;
import com.noseapp.noseapp.WS.Volley.VolleyProcesadordeResultado;
import com.noseapp.noseapp.WS.Volley.VolleyTiposdeError;
import com.noseapp.noseapp.Welcome;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private EditText ed_password, ed_user;
    private Button btn_log;
    private TextView txt_reg;
    private ProgressBar barra;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        barra = (ProgressBar) findViewById(R.id.loading);
        ed_password = (EditText) findViewById(R.id.ed_password);
        ed_user = (EditText) findViewById(R.id.ed_user);
        btn_log = (Button) findViewById(R.id.btnlog);
        txt_reg = (TextView) findViewById(R.id.txt_reg);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        oyentes();

        txt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistarOpc.class);
                startActivity(intent);

            }
        });
    }

    private void oyentes() {
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = ed_user.getText().toString();
                String password = ed_password.getText().toString();
                if (usuario.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.v_usuario, Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.v_clave, Toast.LENGTH_LONG).show();
                    return;
                }
                barra.setVisibility(View.VISIBLE);
                HashMap<String, String> mapa = new HashMap<>();
                mapa.put("nombre", usuario);
                mapa.put("clave", password);
                VolleyPeticion<LocalJson> inicio = Conexion.inciarSesionLocal(
                        getApplicationContext(),
                        mapa,
                        new Response.Listener<LocalJson>() {
                            @Override
                            public void onResponse(LocalJson response) {
                                if (response != null) {
                                    InicioActivity.TOKEN = response.token;
                                    InicioActivity.ID_EXTERNAL = response.external_id;
                                    InicioActivity.NOMBRE_WELCOME = response.nombre;
                                    InicioActivity.TIPO = "L";
                                    barra.setVisibility(View.GONE);
                                    Intent intent = new Intent(LoginActivity.this, Welcome.class);
                                    startActivity(intent);
                                } else if (response == null) {
                                    logClient();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                barra.setVisibility(View.GONE);
                            }
                        }
                );
                requestQueue.add(inicio);

            }
        });
    }

    private void logClient() {
        String u = ed_user.getText().toString();
        String p = ed_password.getText().toString();
        barra.setVisibility(View.VISIBLE);
        HashMap<String, String> map = new HashMap<>();
        map.put("user", u);
        map.put("clave", p);
        VolleyPeticion<ClienteJson> inicioCli = Conexion.inciarSesionClient(
                getApplicationContext(),
                map,
                new Response.Listener<ClienteJson>() {
                    @Override
                    public void onResponse(ClienteJson response) {
                        if (response != null) {
                            InicioActivity.TOKEN = response.token;
                            InicioActivity.ID_EXTERNAL = response.external_id;
                            InicioActivity.NOMBRE_WELCOME = response.nombres;
                            InicioActivity.TIPO = "C";
                            barra.setVisibility(View.GONE);
                            Intent intent = new Intent(LoginActivity.this, Welcome.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Datos Invalidos VERIFIQUELOS PORFAVOR", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        barra.setVisibility(View.GONE);
                        VolleyTiposdeError errores = VolleyProcesadordeResultado.parseErrorResponse(error);
                        Toast.makeText(getApplicationContext(), errores.errorMessage, Toast.LENGTH_SHORT).show();
                        barra.setVisibility(View.GONE);
                    }
                }
        );
        requestQueue.add(inicioCli);
    }
}