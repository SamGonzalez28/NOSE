package com.noseapp.noseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.Result;
import com.noseapp.noseapp.Menu.Listar_menuActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LectorQR extends AppCompatActivity {
    EditText codigo;
    Button escaner;
    private ZXingScannerView vistaescaner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_qr);
    }
    public void Escanear(View view){
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
            codigo = (EditText) findViewById(R.id.edCodigo);
            codigo.setText(dato);
            InicioActivity.ID_EXTERNAL_QR = dato;
            Intent intent = new Intent(LectorQR.this, Listar_menuActivity.class);
            startActivity(intent);
        }
    }
}
