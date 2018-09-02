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
import com.noseapp.noseapp.WS.Adaptador.ListaMenuCli;
import com.noseapp.noseapp.WS.Adaptador.ListadeMenusAdap;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Actividad encargada de listar menus,
 * para que el cliente pueda elegir de entre los menus del local
 */
public class ListarMenuParaCliente extends AppCompatActivity {

    private ListView mi_lista;
    private ListaMenuCli listadeMenusAdap;

    private FloatingActionButton btn_carrito;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_menu_para_cliente);

        mi_lista = (ListView) findViewById(R.id.mi_lista);
        mi_lista.setEmptyView(findViewById(android.R.id.empty));
        listadeMenusAdap = new ListaMenuCli(this);
        mi_lista.setAdapter(listadeMenusAdap);
        queue = Volley.newRequestQueue(this);

        oyente();



        /// QUIERO A UN ARRAYLIST AGREGAR EL PRECIO Y DESCRIPCION DE CADA MENU QUE CLICKEE
        /// PARA CON ESAS OPCIONES EN EL ACTIVITY DE CARRITO GENERAR UNA TABLA
        /// DONDE SE MUESTRE LOS PLATOS QUE ESCOGIÓ Y CON UN SPINNER ESOJA LA CANTIDAD DE PLATOS
        /// Y USAR ESOS DATOS PARA ENVIARLE LA NOTIFICACION DE PEDIDO AL LOCAL
        mi_lista.setClickable(true);
        mi_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String descrpMenu= listadeMenusAdap.getItem(position).descripcion;
                InicioActivity.carrito.add(descrpMenu);
                String preMenu = listadeMenusAdap.getItem(position).precio;
                InicioActivity.carrito.add(preMenu);

                Toast.makeText(ListarMenuParaCliente.this, "AGREGADO A CARRITO", Toast.LENGTH_SHORT).show();
            }
        });






        btn_carrito = (FloatingActionButton) findViewById(R.id.btn_flot_comprar);

        btn_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarMenuParaCliente.this, carrito.class);
                startActivity(intent);
            }
        });
    }

    private void oyente() {
        VolleyPeticion<MenuJson[]> menus = Conexion.getListaMenuAdmin(
                getApplicationContext(),
                InicioActivity.TOKEN,
                InicioActivity.ID_EXTERNAL_QR,

                new Response.Listener<MenuJson[]>() {
                    @Override
                    public void onResponse(MenuJson[] response) {

                        listadeMenusAdap = new ListaMenuCli(Arrays.asList(response), getApplicationContext());
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
