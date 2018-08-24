package com.noseapp.noseapp.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.HashMap;

public class RegistrarMenu extends AppCompatActivity {
    EditText precio, descripcion;
    //RadioButton desayuno, almuerzo, cena, entredia, lunch;
    RadioGroup tipoMenu;
    Button btn_regMenu;
    RequestQueue requestQueue;
    TextView tipoM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_menu);

       /* precio = (EditText) findViewById(R.id.ed_pecio);
        descripcion = (EditText) findViewById(R.id.ed_descrip);
        desayuno = (RadioButton) findViewById(R.id.desayuno);
        almuerzo = (RadioButton) findViewById(R.id.almuerzo);
        cena = (RadioButton) findViewById(R.id.cena);
        lunch = (RadioButton) findViewById(R.id.lunch);
        entredia = (RadioButton) findViewById(R.id.entreDia);*/

        tipoMenu = (RadioGroup) findViewById(R.id.tipoMenuk);

        btn_regMenu = (Button) findViewById(R.id.btn_regMenu);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btn_regMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oyente();
            }
        });
    }

    public void oyente() {

        tipoMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.desayuno) {
                    tipoM.setText(R.string.desayuno);
                    accion();
                } else if (checkedId == R.id.almuerzo) {
                    tipoM.setText(R.string.Almuerzo);
                    accion();
                } else if (checkedId == R.id.cena) {
                    tipoM.setText(R.string.Cena);
                    accion();
                } else if (checkedId == R.id.lunch) {
                    tipoM.setText(R.string.Lunch);
                    accion();
                } else if (checkedId == R.id.entreDia) {
                    tipoM.setText(R.string.EntreDia);
                    accion();
                }else{
                    Toast.makeText(getApplicationContext(), "NO SELECCIONA", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void accion() {
        String tipoM = this.tipoM.toString().trim();
        String precioM = this.precio.toString().trim();
        String descrM = this.descripcion.toString().trim();

        HashMap<String, String> mp = new HashMap<>();
        mp.put("tipo", tipoM);
        mp.put("precio", precioM);
        mp.put("descripcion", descrM);
        mp.put("id_local", InicioActivity.ID_EXTERNAL);
        VolleyPeticion<MenuJson> registrarMenu = Conexion.registroMenu(
                getApplicationContext(),
                mp,
                new Response.Listener<MenuJson>() {
                    @Override
                    public void onResponse(MenuJson response) {
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrarMenu.this, OpcMenuLocal.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "NO SE REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(registrarMenu);
    }
}