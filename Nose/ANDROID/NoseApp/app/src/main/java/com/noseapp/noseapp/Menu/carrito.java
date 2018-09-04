package com.noseapp.noseapp.Menu;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.noseapp.noseapp.Cliente.Cliente_Lateral;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.Local.Admin_Lateral;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.Adaptador.ListadeMenusAdap;
import com.noseapp.noseapp.WS.ModelosJson.AsociarClienteJson;
import com.noseapp.noseapp.WS.ModelosJson.CarteraJson;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.ModelosJson.RegistrosJson;
import com.noseapp.noseapp.WS.Volley.Conexion;
import com.noseapp.noseapp.WS.Volley.VolleyPeticion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;


public class carrito extends AppCompatActivity {
    ListView lista;
    private ListadeMenusAdap listadeMenusAdap;
    private FloatingActionButton btn_pedir;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        lista = (ListView) findViewById(R.id.ListaCarrito);

        listadeMenusAdap = new ListadeMenusAdap(this);
        lista.setAdapter(listadeMenusAdap);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, InicioActivity.carrito);
        lista.setAdapter(adapter);

        btn_pedir = (FloatingActionButton) findViewById(R.id.btn_flot_pedir);
        btn_pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder diag = new AlertDialog.Builder(carrito.this);
                diag.setTitle("IMPORTANTE");
                diag.setMessage("¿ CONFIRMAR PEDIDO ?");
                diag.setCancelable(false);

                diag.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String carro = (String) InicioActivity.carrito.toString();
                        String idCli = InicioActivity.ID_EXTERNAL.toString();
                        String idLoc = InicioActivity.ID_EXTERNAL_QR.toString();
                        String valor = InicioActivity.total;

                        Log.e("mensaje", carro + "  -  " + idCli + " - " + idLoc + " - " + valor);
                        HashMap<String, String> mp = new HashMap<>();
                        mp.put("menu", carro);
                        mp.put("id_cliente", idCli);
                        mp.put("id_local", idLoc);
                        mp.put("valor", valor);
                        VolleyPeticion<RegistrosJson> Registr = Conexion.regReg(
                                getApplicationContext(),
                                mp,
                                new Response.Listener<RegistrosJson>() {
                                    @Override
                                    public void onResponse(RegistrosJson response) {
                                        try {
                                            regSesion();
                                            regSaldo();
                                            enviar();
                                            Toast.makeText(carrito.this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(carrito.this, Cliente_Lateral.class);
                                            startActivity(intent);
                                            finish();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(carrito.this, "NO SE REGISTRO", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(carrito.this, Cliente_Lateral.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                        );
                        requestQueue.add(Registr);
                        InicioActivity.carrito.clear();

                    }
                });
                diag.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                diag.show();

            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder diag = new AlertDialog.Builder(carrito.this);
                diag.setTitle("IMPORTANTE");
                diag.setMessage("¿ ELIMINAR ESTE PLATO ?");
                diag.setCancelable(false);
                diag.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InicioActivity.carrito.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                diag.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                diag.show();
                return false;
            }
        });
    }

    public void enviar() throws JSONException {
        String url = "https://fcm.googleapis.com/fcm/send";
        String a = InicioActivity.tokenMsg.toString();
        String carro = InicioActivity.carrito.toString();
        String nombre = "" + InicioActivity.NOMBRE_WELCOME.toString();
        Log.e("hhhhhhhhhh  ", InicioActivity.tokenMsg.toString());

        JSONObject jsonBody = new JSONObject("{\n" +
                "  \"to\": \"" + a + "\",\n" +
                "  \"notification\": {\n" +
                "    \"title\": \"Pedido nuevo\",\n" +
                "    \"body\":\"Se ha ingresado un pedido nuevo. Pulse para ver\"\n" +
                "   },\n" +
                "\"data\": {\n" +
                "  \"titulo\": \"" + nombre + "\",\n" +
                "  \"descripcion\": \""+carro+"\"\n" +
                "}\n" +
                "}\n");
        Log.e("sds", jsonBody.toString());
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getApplicationContext(), "Notificacion enviada", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "key=AAAA6JTQwIM:APA91bG-N8MltFX1-XvFVMdr-hFuxGEi_QKVIFbNKs0JKHSoOY5yBGNZvsSeh1w4IsMMjzFhF_HcNV9YZXo03ufQpPvGdT57y8BfqGD9vBiryUReKIdc6_aRJSC64DTZV-stgDqWHHE-");
                params.put("content-type", "application/json");
                return params;
            }
        };

        requestQueue.add(jsonRequest);
    }

    public void regSesion(){
        HashMap <String, String>mp = new HashMap<>();
        mp.put("id_cliente", InicioActivity.ID_EXTERNAL.toString());
        mp.put("id_local", InicioActivity.ID_EXTERNAL_QR.toString());
        VolleyPeticion<AsociarClienteJson> regSesion = Conexion.regSesion(
                getApplicationContext(),
                mp,
                new Response.Listener<AsociarClienteJson>() {
                    @Override
                    public void onResponse(AsociarClienteJson response) {
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(regSesion);
    }

    public void regSaldo(){
        HashMap<String, String> mp = new HashMap<>();
        mp.put("id_cliente", InicioActivity.ID_EXTERNAL.toString());
        mp.put("id_local", InicioActivity.ID_EXTERNAL_QR.toString());
        mp.put("saldo", InicioActivity.total.toString());
        VolleyPeticion<CarteraJson> regCartera = Conexion.regCartera(
                getApplicationContext(),
                mp,
                new Response.Listener<CarteraJson>() {
                    @Override
                    public void onResponse(CarteraJson response) {
                        Log.e("reg cartera","si");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("reg cartera","no");

                    }
                }
        );
        requestQueue.add(regCartera);
    }
}
