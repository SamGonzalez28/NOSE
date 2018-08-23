package com.noseapp.noseapp.WS.Volley;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;

public class Conexion {
    private static final String index_url ="https://nosews.000webhostapp.com/public";

    public static VolleyPeticion<MenuJson[]> getListaMenu(
            @NonNull final Context context,
            @NonNull Response.Listener<MenuJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ){
        final String url =index_url + "/comida/listar";
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(MenuJson[].class);
        return lista;
    }
}
