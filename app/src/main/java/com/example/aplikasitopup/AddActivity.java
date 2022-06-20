package com.example.aplikasitopup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    private EditText edtNama, edtHarga;
    private Button btnSave;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ProgressDialog progressDialog;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtNama = findViewById(R.id.edtNamabrg);
        edtHarga = findViewById(R.id.edtHargabarang);
        btnSave = findViewById(R.id.btnTambah);

        progressDialog = new ProgressDialog(AddActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");

        btnSave.setOnClickListener(v->{
            if (edtNama.getText().length() > 0 && edtHarga.getText().length() > 0){
            saveData(edtNama.getText().toString(), edtHarga.getText().toString());
            }else{
                Toast.makeText(this, "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if(intent!= null){
            id = intent.getStringExtra("id");
            edtNama.setText(intent.getStringExtra("item"));
            edtHarga.setText(intent.getStringExtra("harga"));
        }

    }

    private void saveData(String name, String harga) {
        Map<String, Object> user =new HashMap<>();
        user.put("item", name);
        user.put("harga", harga);

        progressDialog.show();

        if (id != null){
            db.collection("users").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AddActivity.this,"Gagal", Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
        }else{
            db.collection("users")
                    .add(user)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(AddActivity.this, "Berhasil di tambah", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}