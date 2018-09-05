package com.noseapp.noseapp.WS.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.AsociarClienteJson;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase que adapta el contenido de una consulta para que pueda ser presentado
 * Consulta del modelo AsociarClienteJson
 * Los clientes que pertenecen a un local
 */
public class ListadeAsociarCLientesAdap extends ArrayAdapter<AsociarClienteJson> {

    private List<AsociarClienteJson> datalista;
    Context mContext;

    public ListadeAsociarCLientesAdap(List<AsociarClienteJson> lista, Context context) {
        super(context, R.layout.item_sesion_local, lista);
        this.datalista = lista;
        this.mContext = context;
    }

    public ListadeAsociarCLientesAdap(Context context) {
        super(context,R.layout.item_sesion_local, new ArrayList<AsociarClienteJson>());

        this.datalista = new ArrayList<AsociarClienteJson>();
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View item = inflater.inflate(R.layout.item_sesion_local,null);

        TextView fechaAsc = (TextView) item.findViewById(R.id.txtFechaAsoci);
        fechaAsc.setText(datalista.get(position).fecha.toString());

        TextView nombre = (TextView) item.findViewById(R.id.txt_nombres_c);
        nombre.setText(datalista.get(position).nombres);

        TextView apellidos = (TextView) item.findViewById(R.id.txt_apellidos_c);
        apellidos.setText(datalista.get(position).apellidos);

        TextView usuario = (TextView) item.findViewById(R.id.txt_user_c);
        usuario.setText(datalista.get(position).cliente);

        return item;
    }


}
