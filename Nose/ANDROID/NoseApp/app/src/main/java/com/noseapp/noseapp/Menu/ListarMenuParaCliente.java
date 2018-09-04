package com.noseapp.noseapp.Menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.Adaptador.ListaMenuCli;
import com.noseapp.noseapp.WS.Adaptador.ListadeMenusAdap;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
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
    private TextView txvnom;
    private ListaMenuCli listadeMenusAdap;

    private FloatingActionButton btn_carrito;
    private String e;
    private String preMenu;
    private String descrpMenu;
    private double tot = 0;
    RequestQueue requestQueue;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_menu_para_cliente);
        txvnom = (TextView) findViewById(R.id.txvnomloc);
        mi_lista = (ListView) findViewById(R.id.mi_lista);
        mi_lista.setEmptyView(findViewById(android.R.id.empty));
        listadeMenusAdap = new ListaMenuCli(this);
        mi_lista.setAdapter(listadeMenusAdap);
        queue = Volley.newRequestQueue(getApplicationContext());
        txvnom.setText(InicioActivity.nombreLocal.toString());
        obtenerTokenMsg();

        oyente();


        /// QUIERO A UN ARRAYLIST AGREGAR EL PRECIO Y DESCRIPCION DE CADA MENU QUE CLICKEE
        /// PARA CON ESAS OPCIONES EN EL ACTIVITY DE CARRITO GENERAR UNA TABLA
        /// DONDE SE MUESTRE LOS PLATOS QUE ESCOGIÓ Y CON UN SPINNER ESOJA LA CANTIDAD DE PLATOS
        /// Y USAR ESOS DATOS PARA ENVIARLE LA NOTIFICACION DE PEDIDO AL LOCAL
        mi_lista.setClickable(true);
        mi_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                descrpMenu = listadeMenusAdap.getItem(position).descripcion;
                preMenu = listadeMenusAdap.getItem(position).precio;
                e = "" + descrpMenu;
                if (InicioActivity.carrito.contains(e)) {
                    Toast.makeText(ListarMenuParaCliente.this, "ESTA REPETIDO", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(ListarMenuParaCliente.this);
                    View mView1 = getLayoutInflater().inflate(R.layout.dialog_spinner, null);
                    mBuilder.setTitle("Escoja Cantidad de Platos");
                    final Spinner mSpinner = (Spinner) mView1.findViewById(R.id.spinner);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListarMenuParaCliente.this,
                            android.R.layout.simple_spinner_item,
                            getResources().getStringArray(R.array.cantidades));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpinner.setAdapter(adapter);

                    mBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!mSpinner.getSelectedItem().toString().equalsIgnoreCase("Escoja una opcion..")) {
                                String cant = mSpinner.getSelectedItem().toString();
                                double sub = Double.parseDouble(cant) * Double.parseDouble(preMenu);

                                tot = tot + sub;
                                InicioActivity.total = "" + tot;
                                InicioActivity.carrito.add(e);
                                Toast.makeText(ListarMenuParaCliente.this, "AGREGADO A CARRITO  " + tot + "----" + InicioActivity.carrito, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });

                    mBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    mBuilder.setView(mView1);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();


                }

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

    public void obtenerTokenMsg() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        VolleyPeticion<LocalJson[]> ver = Conexion.obtenerTokenMsg(
                getApplicationContext(),
                InicioActivity.ID_EXTERNAL_QR,
                new Response.Listener<LocalJson[]>() {
                    @Override
                    public void onResponse(LocalJson[] response) {
                        InicioActivity.tokenMsg = response[0].tokenMsg.toString();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "hgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfm", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(ver);
    }

    public void obtenerNombre() {
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        VolleyPeticion<LocalJson[]> ver = Conexion.obtenerNombre(
                getApplicationContext(),
                InicioActivity.ID_EXTERNAL_QR,
                new Response.Listener<LocalJson[]>() {
                    @Override
                    public void onResponse(LocalJson[] response) {
                        InicioActivity.nombreLocal = response[0].nombre.toString();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "hgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfmhgdfmhgfghfvghvghfvghfhfm", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(ver);
    }
}
