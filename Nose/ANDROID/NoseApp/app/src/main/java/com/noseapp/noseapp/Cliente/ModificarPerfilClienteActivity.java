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
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.ClienteJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.HashMap;
/**
 * Esta actividad presenta el perfil de cliente
 * y da la opcion de modificar los satos
 * luego de ingresar la contraseña registrada
 */
public class ModificarPerfilClienteActivity extends AppCompatActivity {

    EditText nombres, apellidos, ci, correo, domicilio, tele, user, pass;

    Button modCli;
    /**
     * Variable para manejar peticiones de Volley
     */
    RequestQueue requestQueue;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_perfil_cliente);


        nombres = (EditText) findViewById(R.id.txt_nombresMod);
        apellidos = (EditText) findViewById(R.id.txt_apellidosMod);
        ci = (EditText) findViewById(R.id.txt_cedulaCliMod);
        correo = (EditText) findViewById(R.id.txt_correoCliMod);
        domicilio = (EditText) findViewById(R.id.txt_direccionCliMod);
        tele = (EditText) findViewById(R.id.txt_tlfCliMod);
        user = (EditText) findViewById(R.id.txt_userCliMod);
        pass = (EditText) findViewById(R.id.txt_passCliMod);

        modCli = (Button) findViewById(R.id.btn_ClienteMod);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        VolleyPeticion<ClienteJson[]> ver = Conexion.verCliente(
                getApplicationContext(),
                InicioActivity.ID_EXTERNAL,
                new Response.Listener<ClienteJson[]>() {
                    @Override
                    public void onResponse(ClienteJson[] response) {
                        String nomCli = response[0].nombres;
                        String apelliCli = response[0].apellidos;
                        String ciCli = response[0].ci;
                        String correoCli = response[0].correo;
                        String domiCli = response[0].direccion;
                        String tlfCli = response[0].telefono;
                        String passCli = response[0].clave;
                        String userCli = response[0].user;

                        nombres.setText(nomCli);
                        apellidos.setText(apelliCli);
                        ci.setText(ciCli);
                        correo.setText(correoCli);
                        domicilio.setText(domiCli);
                        tele.setText(tlfCli);
                        user.setText(userCli);
                        pass.setText(passCli);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "NO SE MUESTRA", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        modCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });
        requestQueue.add(ver);
    }


    public void modificar() {
        String name = this.nombres.getText().toString().trim();
        String lastname = this.apellidos.getText().toString().trim();
        String cedula = this.ci.getText().toString().trim();
        String email = this.correo.getText().toString().trim();
        String direccion = this.domicilio.getText().toString().trim();
        String tlf = this.tele.getText().toString().trim();
        String user = this.user.getText().toString().trim();
        String pass = this.pass.getText().toString().trim();

        HashMap<String, String> mp = new HashMap<>();
        mp.put("nombres", name);
        mp.put("apellidos", lastname);
        mp.put("ci", cedula);
        mp.put("correo", email);
        mp.put("direccion", direccion);
        mp.put("telefono", tlf);
        mp.put("user", user);
        mp.put("clave", pass);
        VolleyPeticion<ClienteJson> modificar = Conexion.modificarCli(
                getApplicationContext(),
                InicioActivity.ID_EXTERNAL,
                InicioActivity.TOKEN,
                mp,
                new Response.Listener<ClienteJson>() {
                    @Override
                    public void onResponse(ClienteJson response) {
                        Toast.makeText(ModificarPerfilClienteActivity.this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarPerfilClienteActivity.this, Cliente_Lateral.class);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ModificarPerfilClienteActivity.this, "NO SE REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(modificar);
    }
}
