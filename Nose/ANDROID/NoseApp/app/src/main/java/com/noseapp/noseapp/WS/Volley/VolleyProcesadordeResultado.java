package com.noseapp.noseapp.WS.Volley;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.noseapp.noseapp.WS.Volley.VolleyProcesadordeResultado;
import com.noseapp.noseapp.utilidades.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Procesa los posibles resultados que puede devolver una peticion
 * @param <T>
 */

public final class VolleyProcesadordeResultado<T> {

    /**
     * Convert a VoleyError object into a more meaningful VolleyTiposError
     *
     * @param error VolleyError
     * @return VolleyTiposError
     */
    @NonNull
    public static VolleyTiposdeError parseErrorResponse(@Nullable VolleyError error) {
        final VolleyTiposdeError filmsError = new VolleyTiposdeError();
        if (error == null) {
            filmsError.errorCode = VolleyTiposdeError.ERR_UNKNOWN;
            filmsError.errorMessage = "Unknown error";
            filmsError.errorTitle = "Error";

        } else {
            int httpCode = error.networkResponse == null ? -2 : error.networkResponse.statusCode;
            filmsError.networkTimeMs = error.getNetworkTimeMs();
            filmsError.httpCode = httpCode;

            if (error instanceof ParseError) {
                filmsError.errorCode = VolleyTiposdeError.ERR_INVALID_RESPONSE;
                filmsError.errorMessage = "Parser error: cannot parse network response";
                filmsError.errorTitle = "Parser error";

            } else if (error instanceof TimeoutError || httpCode == 504) {
                filmsError.errorCode = VolleyTiposdeError.ERR_REQUEST_TIMEOUT;
                filmsError.errorMessage = "Request timeout, hence network response is null";
                filmsError.errorTitle = "Request timeout";

            } else if (error instanceof NoConnectionError) {
                filmsError.errorCode = VolleyTiposdeError.ERR_NETWORK_CONNECTIVITY;
                Throwable cause = error.getCause();
                if (cause != null && util.isNotEmpty(cause.getMessage()))
                    filmsError.errorMessage = cause.getMessage();
                else
                    filmsError.errorMessage = "Internet has problem. Mobile can't access server";
                filmsError.errorTitle = "No connection";

            } else {
                if (error.networkResponse == null) {
                    String errMsg = error.getMessage();
                    filmsError.errorCode = VolleyTiposdeError.ERR_UNKNOWN;
                    filmsError.errorMessage = util.isNotEmpty(errMsg) ? errMsg :
                            "Error of Volley library when network response is null";
                    filmsError.errorTitle = "VolleyError is null";

                } else {
                    try {
                        String data = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                        if (data.contains("Welcome\r\n")) {
                            filmsError.errorCode = VolleyTiposdeError.ERR_UNKNOWN;
                            filmsError.errorMessage = "Wrong link API";
                            filmsError.errorTitle = "Wrong link API";

                        } else {
                            VolleyTiposdeError tmp = new Gson().fromJson(data, VolleyTiposdeError.class);
                            filmsError.errorCode = tmp.errorCode;
                            filmsError.errorMessage = tmp.errorMessage;
                            filmsError.errorTitle = tmp.errorTitle;
                            filmsError.messageTitle = tmp.messageTitle;
                            filmsError.messageBody = tmp.messageBody;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        filmsError.errorCode = VolleyTiposdeError.ERR_UNKNOWN;
                        filmsError.errorMessage = "Don't know error";
                        filmsError.errorTitle = "Don't know error";
                    }
                }

            }

        }

        return filmsError;
    }

    public static <T> Response<T> parseNetworkResponse(NetworkResponse response, Class<T> clazz) {
        String json = "";

        try {
            String datos = new String(response.data);
            if(datos.contains("{\"Search\":")) {
                datos = datos.replace("{\"Search\":","");
                String[] aux = datos.split("],");
                datos = aux[0] + "]";

            }
            Log.i("Respuesta ", new String(datos.getBytes()));
            json = new String(
                    datos.getBytes(),
                    HttpHeaderParser.parseCharset(response.headers));
            if (clazz == String.class) {
                return (Response<T>) Response.success(
                        json,
                        HttpHeaderParser.parseCacheHeaders(response));
            } else if (clazz == JSONObject.class) {
                try {
                    return (Response<T>) Response.success(
                            new JSONObject(json),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            } else if (clazz == NetworkResponse.class) {
                return (Response<T>) Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
            } else {

                return Response.success(
                        new Gson().fromJson(json, clazz),
                        HttpHeaderParser.parseCacheHeaders(response));
            }

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }
}

