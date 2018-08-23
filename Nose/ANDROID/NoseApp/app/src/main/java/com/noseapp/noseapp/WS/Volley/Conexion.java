package com.noseapp.noseapp.WS.Volley;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.noseapp.noseapp.Local.LocalActivity;
import com.noseapp.noseapp.WS.ModelosJson.ClienteJson;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;

import java.util.HashMap;

public class Conexion {
    private static final String index_url = "https://nosews.000webhostapp.com/public";

    public static VolleyPeticion<MenuJson[]> getListaMenu(
            @NonNull final Context context,
            @NonNull Response.Listener<MenuJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comida/listar";
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(MenuJson[].class);
        return lista;
    }

    public static VolleyPeticion<LocalJson> inciarSesionLocal(
            @NonNull final Context context,
            @NonNull final HashMap mapa,
            @NonNull Response.Listener<LocalJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/index.php/comer/entrar";
        VolleyPeticion request = new VolleyPeticion(
                context,
                Request.Method.POST,
                url,
                mapa,
                HashMap.class,
                String.class,
                responseListener,
                errorListener
        );
        request.setResponseClass(LocalJson.class);
        return request;
    }

    public static VolleyPeticion<ClienteJson> inciarSesionClient(
            @NonNull final Context context,
            @NonNull final HashMap mapa,
            @NonNull Response.Listener<ClienteJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/cliente/entrar";
        VolleyPeticion request = new VolleyPeticion(
                context,
                Request.Method.POST,
                url,
                mapa,
                HashMap.class,
                String.class,
                responseListener,
                errorListener
        );
        request.setResponseClass(ClienteJson.class);
        return request;
    }

    public static VolleyPeticion<LocalJson> registroLocal(
            @NonNull final Context context,
            @NonNull final HashMap mapa,
            @NonNull Response.Listener<LocalJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        {
            final String url = index_url + "/comer/nuevo";
            VolleyPeticion request = new VolleyPeticion(
                    context,
                    Request.Method.POST,
                    url,
                    mapa,
                    HashMap.class,
                    String.class,
                    responseListener,
                    errorListener
            );
            request.setResponseClass(LocalJson.class);
            return request;
        }
    }
}