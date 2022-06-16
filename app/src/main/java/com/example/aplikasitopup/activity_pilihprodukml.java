package com.example.aplikasitopup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class activity_pilihprodukml extends AppCompatActivity {

    EditText edtuser;
    Button btn1, btn2;
    String idmole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihprodukml);

        edtuser = findViewById(R.id.edtuser);
        btn1 = findViewById(R.id.bt1mole);
        btn2 = findViewById(R.id.bt2mole);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                idmole = edtuser.getText().toString();

                if (idmole.isEmpty()) {
                    Snackbar.make(view, "Harap isi User ID!!", Snackbar.LENGTH_LONG).show();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Berhasil Masuk Ke Keranjang", Toast.LENGTH_LONG);
                    t.show();

                    Bundle b = new Bundle();

                    b.putString("a", idmole.trim());

                    Intent i = new Intent(getApplicationContext(), activity_bayar.class);
                    i.putExtras(b);
                    startActivity(i);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idmole = edtuser.getText().toString();

                if (idmole.isEmpty()) {
                    Snackbar.make(view, "Harap isi User ID!!", Snackbar.LENGTH_LONG).show();
                } else {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Berhasil Masuk Ke Keranjang", Toast.LENGTH_LONG);
                    t.show();

                    Bundle b = new Bundle();

                    b.putString("a", idmole.trim());

                    Intent j = new Intent(getApplicationContext(), activity_bayar.class);
                    j.putExtras(b);
                    startActivity(j);
                }
            }
        });

    }
}