package com.noseapp.noseapp.Cliente;

import android.content.Intent;
import android.os.Bundle;
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
import com.noseapp.noseapp.WS.Adaptador.ListadeAsociarCLientesAdap;
import com.noseapp.noseapp.WS.ModelosJson.AsociarClienteJson;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.Arrays;
/**
 * Esta actividad permite listar todos los clientes de un local
 */
public class Listar_cliente_localActivity extends AppCompatActivity {

    private ListView mi_lista;

    private ListadeAsociarCLientesAdap listadeMenusAdap;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        mi_lista = (ListView) findViewById(R.id.mi_lista);
        mi_lista.setEmptyView(findViewById(android.R.id.empty));

        listadeMenusAdap = new ListadeAsociarCLientesAdap(this);
        mi_lista.setAdapter(listadeMenusAdap);

        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.btn_flot_nuevo);
        btn.setVisibility(View.GONE);

        queue = Volley.newRequestQueue(this);

        oyente();
    }

    private void oyente() {
        VolleyPeticion<AsociarClienteJson[]> menus = Conexion.getListaClientesporLocal(
                getApplicationContext(),
                InicioActivity.TOKEN,
                InicioActivity.ID_EXTERNAL,

                new Response.Listener<AsociarClienteJson[]>() {
                    @Override
                    public void onResponse(AsociarClienteJson[] response) {

                        listadeMenusAdap = new ListadeAsociarCLientesAdap(Arrays.asList(response), getApplicationContext());
                        mi_lista.setAdapter(listadeMenusAdap);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                getApplicationContext().getString(R.string.sin_cli),
                                Toast.LENGTH_SHORT);

                        toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast1.show();

                        Intent intent = new Intent(Listar_cliente_localActivity.this, Admin_Lateral.class);
                        startActivity(intent);
                        finish();
                    }

                }
        );
        queue.add(menus);
    }
}
