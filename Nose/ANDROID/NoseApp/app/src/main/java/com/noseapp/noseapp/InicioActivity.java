package com.noseapp.noseapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.google.firebase.iid.FirebaseInstanceId;
import com.noseapp.noseapp.Login.LoginActivity;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;

import java.util.ArrayList;
/**
 *  Esta clase controla la pantalla de inicio de la aplicacion
 *  e inicializa las variables de sesion staticas que se
 *  usaran posteriormente
 */

public class InicioActivity extends AppCompatActivity {
    /**
     * Variable que define el tiempo que tarda la actividad de inicio en espera
     */
    private final int splash = 5000;

    /**
     * Variables estaticas que almacenan datos que se usaran posteriormente
     */
    public static String TOKEN = "";
    public static String tokenMsg = "";
    public static String ID_EXTERNAL = "";
    public static String ID_EXTERNAL_QR = "";
    public static String total = "";
    public static String NOMBRE_WELCOME = "";
    public static String TIPO = "";
    public static String itemListaLocal="";
    public static String nombreLocal="";

    public static ArrayList<String> carrito = new ArrayList<>();
    /**
     * Contiene todo lo que que ocurre al iniciarse la actividad
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(InicioActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, splash);
        verifyStoragePermissions(this);

       // Log.e("token...",FirebaseInstanceId.getInstance().getToken().toString());
        // Log.e("VARIABLE",InicioActivity.tokenMsg.toString());
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermissions(Activity activity) {
        int permissionW = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionC = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

        if (permissionW != PackageManager.PERMISSION_GRANTED || permissionC != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}