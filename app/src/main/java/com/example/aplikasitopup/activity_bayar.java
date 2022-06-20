package com.example.aplikasitopup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class activity_bayar extends AppCompatActivity {
    private TextView idmole, diamond, totalbeli, totalharga, nopesan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
        idmole = findViewById(R.id.txId);
        nopesan = findViewById(R.id.noTrx);
        diamond = findViewById(R.id.txDm);
        totalbeli = findViewById(R.id.txBeli);
        totalharga = findViewById(R.id.txTotalhrg);

        ProgressDialog progressDialog = new ProgressDialog(activity_bayar.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");

        Intent intent = getIntent();
        if (intent != null) {
            nopesan.setText(intent.getStringExtra("id"));
            idmole.setText(intent.getStringExtra("userid"));
            diamond.setText(intent.getStringExtra("diamond"));
            totalbeli.setText(intent.getStringExtra("qty"));
            totalharga.setText(intent.getStringExtra("hargapesan"));


        }
    }
}