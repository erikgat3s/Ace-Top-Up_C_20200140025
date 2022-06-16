package com.example.aplikasitopup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class LihatData extends AppCompatActivity {

    private TextView item,harga;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        item = findViewById(R.id.txItem);
        harga = findViewById(R.id.txHarga);

        progressDialog = new ProgressDialog(LihatData.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");

        Intent intent = getIntent();
        if (intent != null){
            item.setText(intent.getStringExtra("item"));
            harga.setText(intent.getStringExtra("harga"));
        }
    }
}