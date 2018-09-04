package com.noseapp.noseapp.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.Local.Admin_Lateral;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.HashMap;

/**
 * Esta actividad se encarga de presentar los datos de un perfil seleccionado
 * a demas de dar la opcion de eliminarlo y modificarlo
 */

public class PerfilMenu extends AppCompatActivity {

    EditText precio, descripcion;
    TextView tipoMenu;
    Button btn_menuMod, btn_eliMod;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_menu);

        precio = (EditText) findViewById(R.id.ed_pecio);
        descripcion = (EditText) findViewById(R.id.ed_descrip);
        tipoMenu = (TextView) findViewById(R.id.ed_tipo);
        btn_menuMod = (Button) findViewById(R.id.btn_menuMod);
        btn_eliMod = (Button) findViewById(R.id.btn_eliMod);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        VolleyPeticion<MenuJson[]> ver = Conexion.verMenu(
                getApplicationContext(),
                InicioActivity.itemListaLocal,
                new Response.Listener<MenuJson[]>() {
                    @Override
                    public void onResponse(MenuJson[] response) {
                        String tipoM = response[0].tipo;
                        String descripcionM = response[0].descripcion;
                        String precioM = response[0].precio;
                        tipoMenu.setText(tipoM);
                        descripcion.setText(descripcionM);
                        precio.setText(precioM);
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
        btn_menuMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyente();
            }
        });
        btn_eliMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar();
            }
        });

    }
    public void oyente(){
        String tipoM = this.tipoMenu.getText().toString().trim();
        String descripcionM = this.descripcion.getText().toString().trim();
        String precioM = this.precio.getText().toString().trim();

        HashMap<String, String> mp = new HashMap<>();
        mp.put("tipo", tipoM);
        mp.put("descripcion", descripcionM);
        mp.put("precio", precioM);
        VolleyPeticion<MenuJson> registrarMenu = Conexion.modificarMenu(
                getApplicationContext(),
                InicioActivity.itemListaLocal,
                InicioActivity.TOKEN,
                mp,
                new Response.Listener<MenuJson>() {
                    @Override
                    public void onResponse(MenuJson response) {
                        Toast.makeText(PerfilMenu.this, "CAMBIO EXITOSO", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PerfilMenu.this, Admin_Lateral.class);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PerfilMenu.this, "NO SE CAMBIÓ", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(registrarMenu);
    }

    public void borrar(){
        HashMap<String, String> mp = new HashMap<>();
        mp.put("estado", "0");
        VolleyPeticion<MenuJson> elimMenu = Conexion.eliminarMenu(
                getApplicationContext(),
                InicioActivity.itemListaLocal,
                InicioActivity.TOKEN,
                mp,
                new Response.Listener<MenuJson>() {
                    @Override
                    public void onResponse(MenuJson response) {
                        Toast.makeText(PerfilMenu.this, "BORRADO EXITOSO", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PerfilMenu.this, Admin_Lateral.class);
                        startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PerfilMenu.this, "NO SE BORRO", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(elimMenu);
    }
}
