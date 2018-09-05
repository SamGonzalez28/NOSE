package com.noseapp.noseapp.Cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.ClienteJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;


public class perfil_cliente_Activity extends AppCompatActivity {
    /**
     * Esta actividad permite visualizar todos los datos de el cliente logeado
     */
    TextView user, nombres, apellidos, cedula, telefono, correo, direcc;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);

        user = (TextView) findViewById(R.id.txt_user);
        nombres = (TextView) findViewById(R.id.txt_nombres);
        apellidos = (TextView) findViewById(R.id.txt_apellidos);
        cedula = (TextView) findViewById(R.id.txt_cedulaCli);
        telefono = (TextView) findViewById(R.id.txt_tlfCli);
        correo = (TextView) findViewById(R.id.txt_correoCli);
        direcc = (TextView) findViewById(R.id.txt_direccionCli);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        VolleyPeticion<ClienteJson[]> ver = Conexion.verCliente(
                getApplicationContext(),
                InicioActivity.ID_EXTERNAL,
                new Response.Listener<ClienteJson[]>() {
                    @Override
                    public void onResponse(ClienteJson[] response) {
                        String userC = response[0].user;
                        String apellidosC = response[0].apellidos;
                        String ced = response[0].ci;
                        String telef = response[0].telefono;
                        String email = response[0].correo;
                        String dom = response[0].direccion;
                        user.setText(userC);
                        apellidos.setText(apellidosC);
                        cedula.setText(ced);
                        telefono.setText(telef);
                        correo.setText(email);
                        direcc.setText(dom);
                        nombres.setText(InicioActivity.NOMBRE_WELCOME);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "NO SE MUESTRA", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(ver);
    }
}