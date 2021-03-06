package com.noseapp.noseapp.WS.Volley;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.noseapp.noseapp.WS.ModelosJson.AsociarClienteJson;
import com.noseapp.noseapp.WS.ModelosJson.CarteraJson;
import com.noseapp.noseapp.WS.ModelosJson.ClienteJson;
import com.noseapp.noseapp.WS.ModelosJson.LocalJson;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;
import com.noseapp.noseapp.WS.ModelosJson.RegistrosJson;

import java.util.HashMap;

public class Conexion {
    /**
     * Variable String index_url recibe la url base del servicio web
     */
    private static final String index_url = "https://nosews.000webhostapp.com/public";

    /**
     * Metodo para recuperar del servicio web la lista de menus por local
     *
     * @param context          contexto actual
     * @param token            la autentificacion de logeo
     * @param id               external id que se concatena a la url
     * @param responseListener en caso de que la conexion sea satisfactoria
     * @param errorListener    en caso de que la conexion no sea satisfactoria
     * @return un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<MenuJson[]> getListaMenuAdmin(
            @NonNull final Context context,
            @NonNull final String token,
            @NonNull final String id,
            @NonNull Response.Listener<MenuJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comida/listar/comer/" + id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(MenuJson[].class);
        try {
            lista.getHeaders().put("Api-Token", token);
        } catch (Exception e) {
            Log.e("Error listar menu", e.toString());
        }
        return lista;
    }

    /**
     * Metodo para recuperar del servicio web la lista de menus por local
     *
     * @param context          contexto actual
     * @param token            la autentificacion de logeo
     * @param id               external id que se concatena a la url
     * @param responseListener en caso de que la conexion sea satisfactoria
     * @param errorListener    en caso de que la conexion no sea satisfactoria
     * @return un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<RegistrosJson[]> getListaRegistrosAdmin(
            @NonNull final Context context,
            @NonNull final String token,
            @NonNull final String id,
            @NonNull Response.Listener<RegistrosJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/compra/listar/comer/" + id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(RegistrosJson[].class);
        try {
            lista.getHeaders().put("Api-Token", token);
        } catch (Exception e) {
            Log.e("Error listar registros", e.toString());
        }
        return lista;
    }

    /**
     * Metodo para recuperar del servicio web la lista de menus por cliente
     *
     * @param context          contexto actual
     * @param token            la autentificacion de logeo
     * @param id               external id que se concatena a la url
     * @param responseListener en caso de que la conexion sea satisfactoria
     * @param errorListener    en caso de que la conexion no sea satisfactoria
     * @return un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<RegistrosJson[]> getListaRegistrosClientes(
            @NonNull final Context context,
            @NonNull final String token,
            @NonNull final String id,
            @NonNull Response.Listener<RegistrosJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/compra/listar/cliente/" + id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(RegistrosJson[].class);
        try {
            lista.getHeaders().put("Api-Token", token);
        } catch (Exception e) {
            Log.e("Error Lista por CLiente", e.toString());
        }
        return lista;
    }

    /**
     * Metodo para recuperar del servicio web la lista de clientes por local
     *
     * @param context          contexto actual
     * @param token            la autentificacion de logeo
     * @param id               external id que se concatena a la url
     * @param responseListener en caso de que la conexion sea satisfactoria
     * @param errorListener    en caso de que la conexion no sea satisfactoria
     * @return un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<AsociarClienteJson[]> getListaClientesporLocal(
            @NonNull final Context context,
            @NonNull final String token,
            @NonNull final String id,
            @NonNull Response.Listener<AsociarClienteJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/login/listar/comer/" + id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(AsociarClienteJson[].class);
        try {
            lista.getHeaders().put("Api-Token", token);
        } catch (Exception e) {
            Log.e("Error listar cliente", e.toString());
        }
        return lista;
    }

    /**
     * Metodo para recuperar del servicio web el login de local
     *
     * @param context          contexto actual
     * @param mapa             para generar un objeto clave-valor
     * @param responseListener en caso de que la conexion sea satisfactoria
     * @param errorListener    en caso de que la conexion no sea satisfactoria
     * @return un objeto de la clase Volley Peticion
     */

    public static VolleyPeticion<LocalJson> inciarSesionLocal(
            @NonNull final Context context,
            @NonNull final HashMap mapa,
            @NonNull Response.Listener<LocalJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comer/entrar";
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

    /**
     * Metodo para recuperar del servicio web el login de cliente
     *
     * @param context          contexto actual
     * @param mapa             para generar un objeto clave-valor
     * @param responseListener en caso de que la conexion sea satisfactoria
     * @param errorListener    en caso de que la conexion no sea satisfactoria
     * @return un objeto de la clase Volley Peticion
     */
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
    /**
     * Metodo para recuperar del Web Service el registro de Local
     * @param context           contexto actual
     * @param mapa              para generar un objeto clave-valor
     * @param responseListener  en caso de que la conexion sea satisfactoria
     * @param errorListener     en caso de que la conexion no sea satisfactoria
     * @return                  un objeto de la clase Volley Peticion
     */
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

    /**
     * Metodo para recuperar del Web Service el registro de cliente
     * @param context           contexto actual
     * @param mapa              para generar un objeto clave-valor
     * @param responseListener  en caso de que la conexion sea satisfactoria
     * @param errorListener     en caso de que la conexion no sea satisfactoria
     * @return                  un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<ClienteJson> registroCliente(
            @NonNull final Context context,
            @NonNull final HashMap mapa,
            @NonNull Response.Listener<ClienteJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        {
            final String url = index_url + "/cliente/nuevo";
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
    }
    /**
     * Metodo para recuperar del Web Service el registro de menu
     * @param context           contexto actual
     * @param mapa              para generar un objeto clave-valor
     * @param responseListener  en caso de que la conexion sea satisfactoria
     * @param errorListener     en caso de que la conexion no sea satisfactoria
     * @return                  un objeto de la clase Volley Peticion
     */

    public static VolleyPeticion<MenuJson> registroMenu(
            @NonNull final Context context,
            @NonNull HashMap<String, String> mapa,
            @NonNull Response.Listener<MenuJson> responseListener,
            @NonNull Response.ErrorListener errorListener) {
        {
            final String url = index_url + "/comida/nuevo";
            VolleyPeticion request = new VolleyPeticion(
                    context,
                    Request.Method.POST,
                    url,
                    mapa,
                    HashMap.class,
                    String.class,
                    responseListener,
                    errorListener);
            request.setResponseClass(MenuJson.class);
            return request;
        }
    }
    /**
     * Metodo para recuperar del Web Service el modificar de local
     * @param context               contexto actual
     * @param id                    el external_id que hace referencia a que se va a modificar
     * @param token                 el token para autentificarse y acceder al metodo
     * @param mapa                  para generar un objeto clave-valor
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return                      un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<LocalJson> modificarLocal(
            @NonNull final Context context,
            @NonNull final String id,
            @NonNull final String token,
            @NonNull HashMap<String, String> mapa,
            @NonNull Response.Listener<LocalJson> responseListener,
            @NonNull Response.ErrorListener errorListener) {
        {
            final String url = index_url + "/comer/cambiar/" + id;
            VolleyPeticion request = new VolleyPeticion(
                    context,
                    Request.Method.POST,
                    url,
                    mapa,
                    HashMap.class,
                    String.class,
                    responseListener,
                    errorListener);
            request.setResponseClass(LocalJson.class);
            try {
                request.getHeaders().put("Api-Token", token);
            } catch (Exception e) {
                Log.e("Error listar cliente", e.toString());
            }
            return request;
        }
    }
    /**
     * Metodo para recuperar del Web Service el modificar de cliente
     * @param context               contexto actual
     * @param id                    el external_id que hace referencia a que se va a modificar
     * @param token                 el token para autentificarse y acceder al metodo
     * @param mapa                  para generar un objeto clave-valor
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return                      un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<ClienteJson> modificarCli(
            @NonNull final Context context,
            @NonNull final String id,
            @NonNull final String token,
            @NonNull HashMap<String, String> mapa,
            @NonNull Response.Listener<ClienteJson> responseListener,
            @NonNull Response.ErrorListener errorListener) {
        {
            final String url = index_url + "/cliente/cambiar/" + id;
            VolleyPeticion request = new VolleyPeticion(
                    context,
                    Request.Method.POST,
                    url,
                    mapa,
                    HashMap.class,
                    String.class,
                    responseListener,
                    errorListener);
            request.setResponseClass(ClienteJson.class);
            try {
                request.getHeaders().put("Api-Token", token);
            } catch (Exception e) {
                Log.e("Error listar cliente", e.toString());
            }
            return request;
        }
    }
    /**
     * Metodo para recuperar del Web Service el modificar de menu
     * @param context               contexto actual
     * @param id                    el external_id que hace referencia a que se va a modificar
     * @param mapa                  para generar un objeto clave-valor
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return                      un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<MenuJson> modificarMenu(
            @NonNull final Context context,
            @NonNull final String id,
            @NonNull final String token,
            @NonNull HashMap<String, String> mapa,
            @NonNull Response.Listener<MenuJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comida/cambiar/" + id;
        VolleyPeticion request = new VolleyPeticion(
                context,
                Request.Method.POST,
                url,
                mapa,
                HashMap.class,
                String.class,
                responseListener,
                errorListener);
        request.setResponseClass(MenuJson.class);
        try {
            request.getHeaders().put("Api-Token", token);
        } catch (Exception e) {
            Log.e("Error listar cliente", e.toString());
        }
        return request;
    }


    /**
     *  Metodo para recuperar del Web Service el ver de cliente
     * @param context               contexto actual
     * @param external_id           el external_id que hace referencia a que se va a visualizar
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return                      un objeto de la clase Volley Peticion
     */

    public static VolleyPeticion<ClienteJson[]> verCliente(
            @NonNull final Context context,
            @NonNull final String external_id,
            @NonNull Response.Listener<ClienteJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/cliente/ver/" + external_id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(ClienteJson[].class);
        return lista;
    }

    /**
     *  Metodo para recuperar del Web Service el ver de local
     * @param context               contexto actual
     * @param external_id           el external_id que hace referencia a que se va a visualizar
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return                      un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<LocalJson[]> verLocal(
            @NonNull final Context context,
            @NonNull final String external_id,
            @NonNull Response.Listener<LocalJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comer/ver/" + external_id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(LocalJson[].class);
        return lista;
    }

    /**
     *  Metodo para recuperar del Web Service el ver de menu
     * @param context               contexto actual
     * @param external_id           el external_id que hace referencia a que se va a visualizar
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return                      un objeto de la clase Volley Peticion
     */

    public static VolleyPeticion<MenuJson[]> verMenu(
            @NonNull final Context context,
            @NonNull final String external_id,
            @NonNull Response.Listener<MenuJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comida/ver/" + external_id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(MenuJson[].class);
        return lista;
    }
    /**
     * Metodo para recuperar del Web Service el eliminar de menu
     * @param context               contexto actual
     * @param id                    el external_id que hace referencia a que se va a eliminar
     * @param mapa                  para generar un objeto clave-valor
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return                      un objeto de la clase Volley Peticion
     */

    public static VolleyPeticion<MenuJson> eliminarMenu(
            @NonNull final Context context,
            @NonNull final String id,
            @NonNull final String token,
            @NonNull HashMap<String, String> mapa,
            @NonNull Response.Listener<MenuJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comida/borrar/" + id;
        VolleyPeticion request = new VolleyPeticion(
                context,
                Request.Method.POST,
                url,
                mapa,
                HashMap.class,
                String.class,
                responseListener,
                errorListener);
        request.setResponseClass(MenuJson.class);
        try {
            request.getHeaders().put("Api-Token", token);
        } catch (Exception e) {
            Log.e("Error listar cliente", e.toString());
        }
        return request;
    }

    /**
     * Metodo para guardar Registros mediante el metodo de Web Service
     * @param context               contexto actual
     * @param mapa                  para generar un objeto clave-valor
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return
     */
    public static VolleyPeticion<RegistrosJson> regReg(
            @NonNull final Context context,
            @NonNull final HashMap mapa,
            @NonNull Response.Listener<RegistrosJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        {
            final String url = index_url + "/compra/nuevo";
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
            request.setResponseClass(RegistrosJson.class);
            return request;
        }
    }

    /**
     * Metodo para recuperar del Web Service el refrescar el token de mensajes del local
     * @param context               contexto actual
     * @param id                    el external_id que hace referencia al local que se refrescara el token de mensajes
     * @param mapa                  para generar un objeto clave-valor
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return                      un objeto de la clase Volley Peticion
     */
    public static VolleyPeticion<LocalJson> refreshToken(
            @NonNull final Context context,
            @NonNull final String id,
            @NonNull HashMap mapa,
            @NonNull Response.Listener<LocalJson> responseListener,
            @NonNull Response.ErrorListener errorListener) {
        {
            final String url = index_url + "/comer/refreshToken/" + id;
            VolleyPeticion request = new VolleyPeticion(
                    context,
                    Request.Method.POST,
                    url,
                    mapa,
                    HashMap.class,
                    String.class,
                    responseListener,
                    errorListener);
            request.setResponseClass(LocalJson.class);
            return request;
        }

    }

    /**
     * Metodo para guardar la Sesion de cliente en el local mediante el metodo de Web Service
     * @param context               contexto actual
     * @param mapa                  para generar un objeto clave-valor
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return
     */

    public static VolleyPeticion<AsociarClienteJson> regSesion(
            @NonNull final Context context,
            @NonNull HashMap<String, String> mapa,
            @NonNull Response.Listener<AsociarClienteJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/login/nuevo";
        VolleyPeticion request = new VolleyPeticion(
                context,
                Request.Method.POST,
                url,
                mapa,
                HashMap.class,
                String.class,
                responseListener,
                errorListener);
        request.setResponseClass(AsociarClienteJson.class);
        return request;
    }

    /**
     * MEtodo para guardar Cartera mediante el metodo de Web Service
     * @param context               contexto actual
     * @param mapa                  para generar un objeto clave-valor
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return
     */
    public static VolleyPeticion<CarteraJson> regCartera(
            @NonNull final Context context,
            @NonNull HashMap<String, String> mapa,
            @NonNull Response.Listener<CarteraJson> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/saldo/nuevo";
        VolleyPeticion request = new VolleyPeticion(
                context,
                Request.Method.POST,
                url,
                mapa,
                HashMap.class,
                String.class,
                responseListener,
                errorListener);
        request.setResponseClass(CarteraJson.class);
        return request;
    }

    /**
     * Metodo para obtener el token del mensaje del local para enviar la notificacion
     * @param context               contexto actual
     * @param external_id           el external del local para obtener el tokenMsg
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return
     */
    public static VolleyPeticion<LocalJson[]> obtenerTokenMsg(
            @NonNull final Context context,
            @NonNull final String external_id,
            @NonNull Response.Listener<LocalJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comer/obtenerTokenMsg/" + external_id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(LocalJson[].class);
        return lista;
    }

    /**
     * Metodo para obtener el nombre del local
     * @param context               contexto actual
     * @param external_id           el external del local para obtener el nombre de local
     * @param responseListener      en caso de que la conexion sea satisfactoria
     * @param errorListener         en caso de que la conexion no sea satisfactoria
     * @return
     */
    public static VolleyPeticion<LocalJson[]> obtenerNombre(
            @NonNull final Context context,
            @NonNull final String external_id,
            @NonNull Response.Listener<LocalJson[]> responseListener,
            @NonNull Response.ErrorListener errorListener
    ) {
        final String url = index_url + "/comer/obtenerNombre/" + external_id;
        VolleyPeticion lista = new VolleyPeticion(
                context,
                Request.Method.GET,
                url,
                responseListener,
                errorListener);
        lista.setResponseClass(LocalJson[].class);
        return lista;
    }

}