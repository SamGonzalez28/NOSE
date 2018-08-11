package com.sam.nose.Cliente;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegCliRequest extends StringRequest{

    private static final String REGISTER_CLIENT_REQUEST_URL="http://localhost/nosews/public/index.php/cliente/nuevo";
    private Map<String,String> param;
    public RegCliRequest(String nombres, String apellidos, String cedula, String email,String direccion, String telefono, String user, String clave, Response.Listener<String> listener){
        super(Method.POST, REGISTER_CLIENT_REQUEST_URL, listener, null);
        param=new HashMap<>();
        param.put("nombres",nombres);
        param.put("apellidos",apellidos);
        param.put("cedula",cedula);
        param.put("email",email);
        param.put("direccion",direccion);
        param.put("telefono",telefono);
        param.put("user",user);
        param.put("clave",clave);
    }

    public Map<String, String> getParam() {
        return param;
    }
}

    