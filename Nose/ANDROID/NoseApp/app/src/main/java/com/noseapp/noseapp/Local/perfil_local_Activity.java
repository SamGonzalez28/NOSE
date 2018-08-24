package com.noseapp.noseapp.Local;

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
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

public class perfil_local_Activity extends AppCompatActivity {
    TextView nombre, dire, RUC, tele, user;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_local);

        user = (TextView) findViewById(R.id.txt_userlocal);
        nombre = (TextView) findViewById(R.id.nombreL);
        dire = (TextView) findViewById(R.id.direccL);
        RUC = (TextView) findViewById(R.id.rucL);
        tele = (TextView) findViewById(R.id.tlfL);

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
        requestQueue.add(ver);
    }
}
