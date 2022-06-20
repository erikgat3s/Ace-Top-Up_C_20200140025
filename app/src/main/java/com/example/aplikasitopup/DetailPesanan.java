package com.example.aplikasitopup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class DetailPesanan extends AppCompatActivity {

    private TextView userid,diamond,qty,hargapesan;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        userid = findViewById(R.id.userid);
        diamond = findViewById(R.id.diamond);
        qty = findViewById(R.id.qty);
        hargapesan = findViewById(R.id.adHarga);

        progressDialog = new ProgressDialog(DetailPesanan.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");

        Intent intent = getIntent();
        if (intent != null){
            userid.setText(intent.getStringExtra("userid"));
            diamond.setText(intent.getStringExtra("diamond"));
            qty.setText(intent.getStringExtra("qty"));
            hargapesan.setText(intent.getStringExtra("hargapesan"));
        }
    }
}