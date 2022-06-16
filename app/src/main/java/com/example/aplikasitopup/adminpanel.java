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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aplikasitopup.adapter.UserAdapter;
import com.example.aplikasitopup.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
public class adminpanel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton btnAdd;

    private List<User> list = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private UserAdapter userAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.addBtn);

        progressDialog = new ProgressDialog(adminpanel.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        userAdapter = new UserAdapter(getApplicationContext(), list);
        userAdapter.setDialog(new UserAdapter.Dialog() {
            public void onClick(int pos) {
                final CharSequence[] dialogItem = {"Edit", "Hapus", "Lihat Data"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(adminpanel.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            /**
                             *  Melemparkan data ke class berikutnya
                             */
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("item", list.get(pos).getItem());
                                intent.putExtra("harga", list.get(pos).getHarga());
                                startActivity(intent);
                                break;
                            case 1:
                                /**
                                 * memanggil class delete data
                                 */
                                deleteData(list.get(pos).getId());
                                break;
                            case 2:
                                Intent intent1 = new Intent(getApplicationContext(), LihatData.class);
                                intent1.putExtra("id", list.get(pos).getId());
                                intent1.putExtra("item", list.get(pos).getItem());
                                intent1.putExtra("harga", list.get(pos).getHarga());
                                startActivity(intent1);
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

        btnAdd.setOnClickListener(v->{
        startActivity(new Intent(getApplicationContext(), AddActivity.class));
        });
        }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
        progressDialog.show();

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDateSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                User user = new User(document.getString("item"), document.getString("harga"));
                                user.setId(document.getId());
                                list.add(user);
                            }
                        }
                    }
                });
    }
    private void deleteData(String id){
    progressDialog.show();
    db.collection("users").document(id)
            .delete()
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(adminpanel.this, "Data gagal di hapus", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}

