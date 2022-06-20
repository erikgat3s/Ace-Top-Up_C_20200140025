package com.example.aplikasitopup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasitopup.model.User;
import com.example.aplikasitopup.model.User1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_pilihprodukml extends AppCompatActivity {

    private List<User1> list;
    int prcDm, qtyDm, totDm;
    EditText edtTot, idmole;
    Button btn1, btn2;
    Spinner listdm;
    String idemel;
    Button btnhitung, btncart;

    TextView priceDm;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    String list_diamond[] = {"1708 Diamonds + 302 Bonus","4003 Diamonds + 827 Bonus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihprodukml);

        idmole = findViewById(R.id.edtuser);
        listdm = findViewById(R.id.pilihdm);
        edtTot = findViewById(R.id.edtJumlahBeli);
        priceDm = findViewById(R.id.txTotal);

        btnhitung = findViewById(R.id.bt1mole);
        btncart = findViewById(R.id.bt2mole);

        progressDialog = new ProgressDialog(activity_pilihprodukml.this);
        progressDialog.setTitle("Loading");
        progressDialog.setTitle("Menyimpan...");

        ArrayAdapter adDiamond = new ArrayAdapter(activity_pilihprodukml.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list_diamond);
        listdm.setAdapter(adDiamond);

        btncart.setOnClickListener(view -> {
            if (idmole.getText().length()>0 && listdm.getSelectedItem().toString().length()>0 && edtTot.getText().length()>0 && priceDm.getText().length()> 0) {
                saveData(idmole.getText().toString(), listdm.getSelectedItem().toString(), edtTot.getText().toString(), priceDm.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent!=null) {
            id = intent.getStringExtra("id");
            idmole.setText(intent.getStringExtra("userid"));
            listdm.setSelected(Boolean.parseBoolean(intent.getStringExtra("diamond")));
            edtTot.setText(intent.getStringExtra("qty"));
            priceDm.setText(intent.getStringExtra("hargapesan"));
        }


    }

    private void saveData(String userid, String diamond, String qty, String hargapesan) {
        Map<String, Object> user = new HashMap<>();
        user.put("userid", userid);
        user.put("diamond", diamond);
        user.put("qty", qty);
        user.put("hargapesan", hargapesan);

        progressDialog.show();
        if (id != null){
            db.collection("pesanan").document(id).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            db.collection("pesanan").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void buttonhitung (View view){
        qtyDm = Integer.parseInt(edtTot.getText().toString());
        idemel = idmole.getText().toString();

        if(listdm.getSelectedItem().toString()=="1708 Diamonds + 302 Bonus"){
            prcDm = 555000;
            totDm = qtyDm * prcDm;
            priceDm.setText(Integer.toString(totDm));
        }else if(listdm.getSelectedItem().toString()== "4003 Diamonds + 827 Bonus"){
            prcDm = 1332000;
            totDm = qtyDm * prcDm;
            priceDm.setText(Integer.toString(totDm));
        }
    }
    public void buttongo (View view) {
        Intent intent = new Intent(activity_pilihprodukml.this,MainActivity.class);
        startActivity(intent);
    }




}

