package com.noseapp.noseapp.Messaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.noseapp.noseapp.InicioActivity;
import com.noseapp.noseapp.R;

public class Pedido extends AppCompatActivity {
    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        infoTextView = (TextView) findViewById(R.id.infoTextView);


        if (getIntent().getExtras() != null){
            for (String key : getIntent().getExtras().keySet()){
                String value = getIntent().getExtras().getString(key);
                infoTextView.append("\n" + key + ":" + value);
            }
        }


    }
}
