package com.noseapp.noseapp.Local;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.R;
import com.noseapp.noseapp.Save;
/**
 * Esta actividad genera un codigo QR correspondiente al external de local
 * que sera usada por el cliente para acceder a dicho local
 */
public class GenerarQr extends AppCompatActivity {


    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_qr);
        final ImageView image = (ImageView) findViewById(R.id.img);
        Button btn_guar = (Button) findViewById(R.id.btn_guardar);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(InicioActivity.ID_EXTERNAL, BarcodeFormat.QR_CODE, 300, 300);
            bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }

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
