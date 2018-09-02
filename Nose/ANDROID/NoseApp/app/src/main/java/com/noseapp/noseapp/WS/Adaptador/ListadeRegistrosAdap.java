package com.noseapp.noseapp.WS.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.RegistrosJson;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase que adapta el contenido de una consulta para que pueda ser presentado
 * Consulta del modelo RegistrosJson
 */
public class ListadeRegistrosAdap extends ArrayAdapter<RegistrosJson> {

    private List<RegistrosJson> datalista;
    Context mContext;

    public ListadeRegistrosAdap(List<RegistrosJson> lista, Context context) {
        super(context, R.layout.item_registros_local, lista);
        this.datalista = lista;
        this.mContext = context;
    }

    public ListadeRegistrosAdap(Context context) {
        super(context,R.layout.item_registros_local_vacio, new ArrayList<RegistrosJson>());

        this.datalista = new ArrayList<RegistrosJson>();
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View item = inflater.inflate(R.layout.item_registros_local,null);

        TextView fecha = (TextView) item.findViewById(R.id.txt_fecha);
        fecha.setText(datalista.get(position).fecha);

        TextView nombre = (TextView) item.findViewById(R.id.txt_nombres);
        nombre.setText(datalista.get(position).local);

        TextView cantidad = (TextView) item.findViewById(R.id.txt_cant);
        cantidad.setText(datalista.get(position).cantidad);

        TextView valor = (TextView) item.findViewById(R.id.txt_valor);
        valor.setText(datalista.get(position).valor);

        TextView descripcion = (TextView) item.findViewById(R.id.txt_descripcion_m);
        descripcion.setText(datalista.get(position).menu);

        TextView precio = (TextView) item.findViewById(R.id.txt_precio);
        precio.setText(datalista.get(position).precio);
        return item;
    }


}
