package com.dita.logintry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity implements View.OnClickListener {
    private EditText NamaPengguna, NoTelp;
    private Button Daftar, SignIn;
    DatabaseReference dbrf, dbrfs;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authListener;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        NamaPengguna = (EditText) findViewById(R.id.brNamaPg);
        NoTelp = (EditText) findViewById(R.id.brNoTlp);
        Daftar = (Button) findViewById(R.id.daftar);
        Daftar.setOnClickListener(this);
        SignIn = (Button) findViewById(R.id.SignIn);
        SignIn.setOnClickListener(this);
        database = FirebaseDatabase.getInstance();
        dbrf = database.getReference("Pengguna");

    }

    @Override
    public void onClick(View view) {
        if(view == Daftar){
            DatabaseReference nilai = dbrf.push();

            String PenggunaNilai = NamaPengguna.getText().toString();
            String NPNilai = NoTelp.getText().toString();

            if(TextUtils.isEmpty(PenggunaNilai)){
                NamaPengguna.setError("masukkan nilai terlebih dahulu");
            }
            else if(TextUtils.isEmpty(NPNilai)){
                NoTelp.setError("masukkan nilai terlebih dahulu");
            }
            else{
                //menambahkan nilai ke column firebase
                nilai.child("Nama Pengguna").setValue(PenggunaNilai);
                nilai.child("No Telepon").setValue(NPNilai);

                Toast.makeText(getApplicationContext(), "Data Berhasil Ditambahkan !", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view == SignIn){
        Intent i = new Intent(getApplicationContext(), login.class);
        startActivity(i);
        }

    }
}