package com.example.aplikasitopup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class LihatData extends AppCompatActivity {

    private TextView item,harga, berapa, totalharga;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        item = findViewById(R.id.txId);
        harga = findViewById(R.id.txDm);
        berapa = findViewById(R.id.txBeli);
        totalharga = findViewById(R.id.txTotalhrg);


        progressDialog = new ProgressDialog(LihatData.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");

        Intent intent = getIntent();
        if (intent != null){
            item.setText(intent.getStringExtra("userid"));
            harga.setText(intent.getStringExtra("diamond"));
            berapa.setText(intent.getStringExtra("qty"));
            totalharga.setText(intent.getStringExtra("hargapesan"));
        }
    }
}