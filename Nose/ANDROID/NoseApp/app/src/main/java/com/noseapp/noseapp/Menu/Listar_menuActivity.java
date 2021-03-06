package com.noseapp.noseapp.Menu;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.Adaptador.ListadeMenusAdap;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.Arrays;
/**
 * Actividad encargada de listar menus,
 * ademas de tener opcion de eliminar y modificar
 * contiene un boton flotante para añadir uno nuevo
 */
public class Listar_menuActivity extends AppCompatActivity {

    private ListView mi_lista;

    private FloatingActionButton btn_nuevo;

    private ListadeMenusAdap listadeMenusAdap;

    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        mi_lista = (ListView) findViewById(R.id.mi_lista);
        mi_lista.setClickable(true);
        mi_lista.setEmptyView(findViewById(android.R.id.empty));

        listadeMenusAdap = new ListadeMenusAdap(this);
        mi_lista.setAdapter(listadeMenusAdap);

        mi_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Listar_menuActivity.this ,PerfilMenu.class);
                String extMenu = listadeMenusAdap.getItem(position).external_id;
                InicioActivity.itemListaLocal = extMenu;
                startActivity(intent);
                finish();
            }
        });


         btn_nuevo = (FloatingActionButton) findViewById(R.id.btn_flot_nuevo);

         btn_nuevo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(Listar_menuActivity.this, RegistrarMenu.class);
                 startActivity(intent);
                 finish();
             }
         });

        queue = Volley.newRequestQueue(this);

        oyente();
    }

    private void oyente() {
        VolleyPeticion<MenuJson[]> menus = Conexion.getListaMenuAdmin(
                getApplicationContext(),
                InicioActivity.TOKEN,
                InicioActivity.ID_EXTERNAL,

                new Response.Listener<MenuJson[]>() {
                    @Override
                    public void onResponse(MenuJson[] response) {

                        listadeMenusAdap = new ListadeMenusAdap(Arrays.asList(response), getApplicationContext());
                        mi_lista.setAdapter(listadeMenusAdap);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                getApplicationContext().getString(R.string.noseencontro),
                                Toast.LENGTH_SHORT);

                        toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast1.show();
                    }

                }
        );
        queue.add(menus);
    }
}
