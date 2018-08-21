package com.noseapp.noseapp.Menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.Adaptador.ListadeMenusAdap;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.Arrays;

public class Listar_menuActivity extends AppCompatActivity {

    private ListView mi_lista;
    private Button boton;

    private ListadeMenusAdap listadeMenusAdap;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_menu);

        mi_lista = (ListView) findViewById(R.id.mi_lista);
        boton = (Button) findViewById(R.id.btn_lista);

        listadeMenusAdap = new ListadeMenusAdap(this);
        mi_lista.setAdapter(listadeMenusAdap);

        queue = Volley.newRequestQueue(this);

        oyente();
    }

    private void consultarWS() {
        VolleyPeticion<MenuJson[]> menus = Conexion.getListaMenu(this,

                new Response.Listener<MenuJson[]>() {
                    @Override
                    public void onResponse(MenuJson[] response) {
                        Log.i("ERROR","unonnnn");
                        listadeMenusAdap = new ListadeMenusAdap(Arrays.asList(response), getApplicationContext());
                        Log.i("ERROR","Otra consua");
                        mi_lista.setAdapter(listadeMenusAdap);
                        Log.i("ERROR ****",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                getApplicationContext().getString(R.string.noseencontro),
                                Toast.LENGTH_SHORT);

                        toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        Log.i("ERROR",error.getMessage());
                        toast1.show();
                    }

                }
        );
       queue.add(menus);
    }

    public void oyente(){
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarWS();
            }
        });
    }
}
