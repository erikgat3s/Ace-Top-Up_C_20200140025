package com.example.aplikasitopup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aplikasitopup.adapter.UserAdapter;
import com.example.aplikasitopup.adapter.UserAdapter1;
import com.example.aplikasitopup.model.User;
import com.example.aplikasitopup.model.User1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Keranjang extends AppCompatActivity {
    private RecyclerView recyclerView;
    FloatingActionButton fab;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private List<User1> list = new ArrayList<>();
    private UserAdapter1 userAdapter1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.btn_add);

        progressDialog = new ProgressDialog(Keranjang.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        userAdapter1 = new UserAdapter1(getApplicationContext(), list);
        userAdapter1.setDialog1(new UserAdapter1.Dialog() {
            @Override
            public void onClick(final int pos) {
                final CharSequence[] dialogItem = {"Edit", "Hapus", "Lihat Data", "Checkout"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(Keranjang.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), activity_pilihprodukml.class);
                                intent.putExtra("id", list.get(pos).getId1());
                                intent.putExtra("userid", list.get(pos).getUserid());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(list.get(pos).getId1());
                                break;

                            case 2:
                                Intent intent1 = new Intent(getApplicationContext(), LihatData.class);
                                intent1.putExtra("id", list.get(pos).getId1());
                                intent1.putExtra("userid", list.get(pos).getUserid());
                                intent1.putExtra("diamond", list.get(pos).getDiamond());
                                intent1.putExtra("qty", list.get(pos).getQty());
                                intent1.putExtra("hargapesan", list.get(pos).getHargapesan());
                                startActivity(intent1);
                                break;
                            case 3:
                                Intent intent2 = new Intent(getApplicationContext(), activity_bayar.class);
                                intent2.putExtra("id", list.get(pos).getId1());
                                intent2.putExtra("userid", list.get(pos).getUserid());
                                intent2.putExtra("diamond", list.get(pos).getDiamond());
                                intent2.putExtra("qty", list.get(pos).getQty());
                                intent2.putExtra("hargapesan", list.get(pos).getHargapesan());
                                deleteData(list.get(pos).getId1());
                                startActivity(intent2);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(userAdapter1);

        fab.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), activity_pilihprodukml.class));
        });
    }


    @Override
    protected void onStart(){
        super.onStart();
        getData();
    }


    private void getData() {
        progressDialog.show();

        db.collection("pesanan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list.clear();
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        User1 user = new User1(document.getString("userid"), document.getString("diamond"), document.getString("qty"), document.getString("hargapesan"));
                        user.setId1(document.getId());
                        list.add(user);
                    }
                    userAdapter1.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "Data gagal di ambil!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }


    private void deleteData(String id){
        progressDialog.show();
        db.collection("pesanan").document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Data gagal di hapus!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                getData();
            }
        });
    }
}