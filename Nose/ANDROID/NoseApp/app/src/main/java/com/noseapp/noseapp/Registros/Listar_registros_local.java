package com.noseapp.noseapp.Registros;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.Local.Admin_Lateral;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.Adaptador.RegistrosAdapLocal;
import com.noseapp.noseapp.WS.ModelosJson.RegistrosJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.Arrays;

/**
 * Actividad encargada de presentar una lista con los registros de locales
 */
public class Listar_registros_local extends AppCompatActivity {

    private ListView mi_lista;

    private RegistrosAdapLocal listadeRegistrosAdap;

    private RequestQueue queue;
    private FloatingActionButton btn_nuevo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        mi_lista = (ListView) findViewById(R.id.mi_lista);
        btn_nuevo = (FloatingActionButton) findViewById(R.id.btn_flot_nuevo);
        btn_nuevo.setVisibility(View.GONE);

        listadeRegistrosAdap = new RegistrosAdapLocal(this);
        mi_lista.setAdapter(listadeRegistrosAdap);

        queue = Volley.newRequestQueue(this);

        oyente();

    }
    private void oyente() {
        VolleyPeticion<RegistrosJson[]> registros = Conexion.getListaRegistrosAdmin(
                getApplicationContext(),
                InicioActivity.TOKEN,
                InicioActivity.ID_EXTERNAL,

                new Response.Listener<RegistrosJson[]>() {
                    @Override
                    public void onResponse(RegistrosJson[] response) {
                        listadeRegistrosAdap = new RegistrosAdapLocal(Arrays.asList(response),getApplicationContext());
                        mi_lista.setAdapter(listadeRegistrosAdap);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                getApplicationContext().getString(R.string.sin_regi),
                                Toast.LENGTH_SHORT);
                        toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast1.show();

                        Intent intent = new Intent(Listar_registros_local.this, Admin_Lateral.class);
                        startActivity(intent);
                        finish();
                    }

                }
        );
        queue.add(registros);
    }
}
