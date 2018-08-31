package com.noseapp.noseapp.Local;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.Cliente.ModificarPerfilClienteActivity;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.Login.LoginActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.HashMap;

public class ModificarPerfilLocalActivity extends AppCompatActivity {

    /**
     * Variables para los TextView a utilizar en el layout de modificar local
     */
     private EditText nombre, dire, RUC, tele;
     private TextView user;
    /**
     * Variables para el Button a utilizar en el layout de modificar local
     */
    private Button btn_modifi;
    /**
     * Variable para manejar peticiones de Volley
     */
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_perfil_local);

        user = (TextView) findViewById(R.id.txt_userlocalmod);
        nombre = (EditText) findViewById(R.id.nombreLmod);
        dire = (EditText) findViewById(R.id.direccLmod);
        RUC = (EditText) findViewById(R.id.rucLmod);
        tele = (EditText) findViewById(R.id.tlfLmod);

        btn_modifi = (Button) findViewById(R.id.btn_modLocal);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        VolleyPeticion<LocalJson[]> ver = Conexion.verLocal(
                getApplicationContext(),
                InicioActivity.ID_EXTERNAL,
                new Response.Listener<LocalJson[]>() {
                    @Override
                    public void onResponse(LocalJson[] response) {
                        String userL = response[0].nombre;
                        String nameL = response[0].nombre;
                        String rucLo = response[0].ruc;
                        String tlfL = response[0].telefono;
                        String dirL = response[0].direccion;

                        user.setText(userL);
                        nombre.setText(nameL);
                        RUC.setText(rucLo);
                        tele.setText(tlfL);
                        dire.setText(dirL);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "NO SE MUESTRA", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        btn_modifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar();
            }
        });
        requestQueue.add(ver);
    }

    public void modificar() {
        String name = this.nombre.getText().toString().trim();
        String RUC = this.RUC.getText().toString().trim();
        String Direccion = this.dire.getText().toString().trim();
        String tlf = this.tele.getText().toString().trim();

        HashMap<String, String> mp = new HashMap<>();
        mp.put("nombre", name);
        mp.put("ruc", RUC);
        mp.put("direccion", Direccion);
        mp.put("telefono", tlf);
        VolleyPeticion<LocalJson> Local = Conexion.modificarLocal(
                getApplicationContext(),
                InicioActivity.ID_EXTERNAL,
                InicioActivity.TOKEN,
                mp,
                new Response.Listener<LocalJson>() {
                    @Override
                    public void onResponse(LocalJson response) {
                        Toast.makeText(ModificarPerfilLocalActivity.this, "CAMBIO EXITOSO", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarPerfilLocalActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ModificarPerfilLocalActivity.this, "NO SE REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(Local);
    }
}
