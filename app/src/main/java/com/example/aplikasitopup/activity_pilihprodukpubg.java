package com.example.aplikasitopup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class activity_pilihprodukpubg extends AppCompatActivity {

    EditText edtidpubg;
    String idpubg;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihprodukpubg);
        edtidpubg = findViewById(R.id.edtidpubg);
        btn1 = findViewById(R.id.bt1pubg);
        btn2 = findViewById(R.id.bt2pubg);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idpubg = edtidpubg.getText().toString();

                if (idpubg.isEmpty()) {
                    Snackbar.make(view, "Harap isi User ID!!", Snackbar.LENGTH_LONG).show();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Berhasil Masuk Ke Keranjang", Toast.LENGTH_LONG);
                    t.show();

                    Bundle b = new Bundle();

                    b.putString("a", idpubg.trim());

                    Intent i = new Intent(getApplicationContext(), activity_bayar.class);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idpubg = edtidpubg.getText().toString();

                if (idpubg.isEmpty()) {
                    Snackbar.make(view, "Harap isi User ID!!", Snackbar.LENGTH_LONG).show();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Berhasil Masuk Ke Keranjang", Toast.LENGTH_LONG);
                    t.show();

                    Bundle b = new Bundle();

                    b.putString("a", idpubg.trim());

                    Intent i = new Intent(getApplicationContext(), activity_bayar.class);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });
    }
}