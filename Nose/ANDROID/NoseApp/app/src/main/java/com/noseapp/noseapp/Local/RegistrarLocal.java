package com.noseapp.noseapp.Local;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarLocal extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private TextView nombre, ruc, domicilio, telefono, password;
    private Button btn_regLocal;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;


    /**
     * Instancia la creacion de la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_local);

        nombre = (TextView) findViewById(R.id.ed_nombreLocal);
        ruc = (TextView) findViewById(R.id.ruc);
        domicilio = (TextView) findViewById(R.id.direccionLocal);
        telefono = (TextView) findViewById(R.id.tlfLocal);
        password = (TextView) findViewById(R.id.passwordLocal);

        btn_regLocal = (Button) findViewById(R.id.btn_regLocal);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btn_regLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWS();
            }
        });
    }



    private void cargarWS(){

        String url = "https://nosews.000webhostapp.com/public/comer/nuevo?" +
                "&nombre="   +nombre.getText().toString()+
                "&direccion="+domicilio.getText().toString() +
                "&ruc="      +ruc.getText().toString()+
                "&telefono=" +telefono.getText().toString()+
                "&clave="    +password.getText().toString();

        url = url.replace(" ", "%20");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,null,this,this);
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * Generado por defecto por Volley devuelve respuesta en caso de error
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se registro",Toast.LENGTH_SHORT).show();
    }

    /**
     * Generado por defecto por Volley devuelve respuesta en caso satisfactorio
     * @param response
     */
    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(),"Se registro",Toast.LENGTH_SHORT).show();
        nombre.setText("");
        domicilio.setText("");
        ruc.setText("");
        telefono.setText("");
        password.setText("");

    }
}
