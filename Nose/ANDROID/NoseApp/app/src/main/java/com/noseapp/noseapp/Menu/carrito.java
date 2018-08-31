package com.noseapp.noseapp.Menu;

import android.app.NotificationManager;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.WS.Adaptador.ListadeMenusAdap;


public class carrito extends AppCompatActivity {
    ListView lista;
    private ListadeMenusAdap listadeMenusAdap;
    private FloatingActionButton btn_pedir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        lista = (ListView) findViewById(R.id.ListaCarrito);

        listadeMenusAdap = new ListadeMenusAdap(this);
        lista.setAdapter(listadeMenusAdap);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, InicioActivity.carrito);
        lista.setAdapter(adapter);

        btn_pedir = (FloatingActionButton) findViewById(R.id.btn_flot_pedir);
        btn_pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });

    }

    public void enviar() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.restaurant);
        mBuilder.setContentTitle(""+InicioActivity.NOMBRE_WELCOME);
        mBuilder.setContentText(""+InicioActivity.carrito);
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION, mBuilder.build());
    }

    private static final int MY_NOTIFICATION = 100;
}
