package com.noseapp.noseapp.Local;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.Save;

public class GenerarQr extends AppCompatActivity {


    String text2Qr;
    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_qr);

        final EditText text = (EditText) findViewById(R.id.ed_text);
        Button btn_gen = (Button) findViewById(R.id.btn_gen);
        final ImageView image = (ImageView) findViewById(R.id.img);
        Button btn_guar = (Button) findViewById(R.id.btn_guardar);
        btn_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2Qr = text.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE, 300, 300);
                    bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    image.setImageBitmap(bitmap);
                }catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });

        btn_guar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.buildDrawingCache();
                Bitmap bmap = image.getDrawingCache();
                Save saveFile = new Save();
                saveFile.SaveImage(getBaseContext(),bmap);
            }
        });




    }
}
