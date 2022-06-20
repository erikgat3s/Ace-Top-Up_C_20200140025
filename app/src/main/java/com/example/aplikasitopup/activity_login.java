package com.example.aplikasitopup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {

    Button btnLogin;
    EditText edUser, edPass;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btSignin);
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPassword);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            user = edUser.getText().toString();
            pass = edPass.getText().toString();

            String name = "123";
            String pass = "123";

            if(user.isEmpty() || pass.isEmpty()){
                Toast t = Toast.makeText(getApplicationContext(),
                        "Username dan Password Wajib Diisi!", Toast.LENGTH_LONG);
                t.show();
            }else
                if (user.equals(name) && pass.equals(pass)) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Login Sukses",
                            Toast.LENGTH_LONG);
                    t.show();

                    Bundle b = new Bundle();
                    b.putString("a", user.trim());

                    Intent i = new Intent(getApplicationContext(), adminpanel.class);
                    i.putExtras(b);
                    startActivity(i);
                } else {
                    Toast t = Toast.makeText(getApplicationContext(),
                        "Login Gagal", Toast.LENGTH_LONG);
                    t.show();
                }

            }
        });
    }
}

