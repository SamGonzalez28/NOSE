package com.noseapp.noseapp.WS.Adaptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.ModelosJson.MenuJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que adapta el contenido de una consulta para que pueda ser presentado
 * Consulta del modelo MenuJson
 * los menus que pertenecen a un local
 */
public class ListadeMenusAdap extends ArrayAdapter<MenuJson> {

    private List<MenuJson> datalista;
    Context mContext;

    public ListadeMenusAdap(List<MenuJson> lista, Context context) {
        super(context, R.layout.item_menu_local, lista);
        this.datalista = lista;
        this.mContext = context;
    }

    public ListadeMenusAdap(Context context) {
        super(context, R.layout.item_menu_vacio_local, new ArrayList<MenuJson>());

        this.datalista = new ArrayList<MenuJson>();
        this.mContext = context;
    }
    /**
     * Asigna los valoress consultados a la vista respectiva
     * @param position          posicion en la que se colocaran
     * @param convertView       contexto actual
     * @param parent            el layout al que se adaptara
     * @return                  retorna el item con la consulta
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.item_menu_local, null);

        TextView precio = (TextView) item.findViewById(R.id.txt_precio);
        precio.setText(datalista.get(position).precio);
        TextView descripcion = (TextView) item.findViewById(R.id.txt_descripcion);
        descripcion.setText(datalista.get(position).descripcion);

        return item;
    }
}
