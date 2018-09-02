package com.noseapp.noseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.Result;
import com.noseapp.noseapp.Menu.ListarMenuParaCliente;
import com.noseapp.noseapp.Menu.Listar_menuActivity;
import com.noseapp.noseapp.Registros.Listar_registros_cliente;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Clase utilizada para Leer el QR
 */
public class LectorQR extends AppCompatActivity {
    TextView codigo;
    private ZXingScannerView vistaescaner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_qr);

        vistaescaner = new ZXingScannerView(this);
        vistaescaner.setResultHandler(new zxingscanner());
        setContentView(vistaescaner);
        vistaescaner.startCamera();

    }
    class zxingscanner implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(Result result) {
            String dato = result.getText();
            setContentView(R.layout.activity_lector_qr);
            vistaescaner.stopCamera();
            codigo = (TextView) findViewById(R.id.edCodigo);
            codigo.setText(dato);
            InicioActivity.ID_EXTERNAL_QR = dato;
            Intent intent = new Intent(LectorQR.this, ListarMenuParaCliente.class);
            startActivity(intent);
            finish();
        }
    }
}
