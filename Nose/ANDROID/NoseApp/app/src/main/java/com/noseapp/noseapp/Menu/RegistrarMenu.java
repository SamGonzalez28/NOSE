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
import com.noseapp.noseapp.Local.Admin_Lateral;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import java.util.HashMap;

public class RegistrarMenu extends AppCompatActivity {
    EditText precio, descripcion;
    RadioButton desayuno, almuerzo, cena, entredia, lunch;
    RadioGroup tipoMenu;
    Button btn_regMenu;
    RequestQueue requestQueue;
    String menu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_menu);

        precio = (EditText) findViewById(R.id.ed_pecio);
        descripcion = (EditText) findViewById(R.id.ed_descrip);

        almuerzo = (RadioButton) findViewById(R.id.almuerzo);
        cena = (RadioButton) findViewById(R.id.cena);
        lunch = (RadioButton) findViewById(R.id.lunch);
        entredia = (RadioButton) findViewById(R.id.entreDia);

        tipoMenu = (RadioGroup) findViewById(R.id.tipoMenuk);
        desayuno = (RadioButton) findViewById(R.id.desayuno);
        btn_regMenu = (Button) findViewById(R.id.btn_regMenu);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btn_regMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accion();
                btn_regMenu.setEnabled(false);
            }
        });
        oyente();
    }

    public void oyente() {

        tipoMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.desayuno) {
                    menu = getApplicationContext().getString(R.string.desayuno);
                } else if (checkedId == R.id.almuerzo) {
                    menu = getApplicationContext().getString(R.string.Almuerzo);
                } else if (checkedId == R.id.cena) {
                    menu = getApplicationContext().getString(R.string.Cena);
                } else if (checkedId == R.id.lunch) {
                    menu = getApplicationContext().getString(R.string.Lunch);
                } else if (checkedId == R.id.entreDia) {
                    menu = getApplicationContext().getString(R.string.EntreDia);
                } else {
                    Toast.makeText(getApplicationContext(), "NO SELECCIONA", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void accion() {
        Log.i("accion", menu);
        if (menu.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "SELECCIONE UN TIPO DE MENU", Toast.LENGTH_SHORT).show();
            return;
        } else {
            HashMap<String, String> mp = new HashMap<>();
            mp.put("tipo", menu);
            mp.put("precio", precio.getText().toString().trim());
            mp.put("descripcion", descripcion.getText().toString().trim());
            mp.put("local", InicioActivity.ID_EXTERNAL);
            VolleyPeticion<MenuJson> registrarMenu = Conexion.registroMenu(
                    getApplicationContext(),
                    mp,
                    new Response.Listener<MenuJson>() {
                        @Override
                        public void onResponse(MenuJson response) {
                            Toast.makeText(getApplicationContext(), "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrarMenu.this, Admin_Lateral.class);
                            startActivity(intent);
                            finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "NO SE REGISTRO", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrarMenu.this, Admin_Lateral.class);
                            startActivity(intent);
                            finish();
                            btn_regMenu.setEnabled(true);
                        }
                    }
            );
            requestQueue.add(registrarMenu);
        }

    }
}