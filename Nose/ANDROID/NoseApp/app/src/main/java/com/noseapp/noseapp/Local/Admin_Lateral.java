package com.noseapp.noseapp.Local;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.noseapp.noseapp.Cliente.Listar_cliente_localActivity;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.Login.LoginActivity;
import com.noseapp.noseapp.Menu.Listar_menuActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.Registros.Listar_registros_local;

/**
 * Esta actividad se encarga de el control y redireccion de las opciones que contiene
 * la barra lateral de Local
 */
public class Admin_Lateral extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__lateral);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**
         * llamado a los botones de direccion del menu
         */


        ImageButton menu = (ImageButton) findViewById(R.id.btn_menus);
        ImageButton registros = (ImageButton) findViewById(R.id.btn_registros);
        ImageButton clientes = (ImageButton) findViewById(R.id.btn_clientes);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Lateral.this, Listar_menuActivity.class);
                startActivity(intent);
            }
        });

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Lateral.this, Listar_cliente_localActivity.class);
                startActivity(intent);
            }
        });

        registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Lateral.this, Listar_registros_local.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin__lateral, menu);
        TextView nombre = (TextView) findViewById(R.id.nombredeUsuario);
        nombre.setText(InicioActivity.NOMBRE_WELCOME);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_perfil_local) {

            Intent intent = new Intent(Admin_Lateral.this, perfil_local_Activity.class);
            startActivity(intent);

        } else if (id == R.id.nav_editar_perfil_local) {

            Intent intent = new Intent(Admin_Lateral.this, ModificarPerfilLocalActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_qr_local) {

            Intent intent = new Intent(Admin_Lateral.this, GenerarQr.class);
            startActivity(intent);

        } else if (id == R.id.nav_cerrar_local) {
            Intent intent = new Intent(Admin_Lateral.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
