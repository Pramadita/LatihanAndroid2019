package com.dita.email;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText email, pw;
    private Button Masuk, Daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.Email);
        pw = findViewById(R.id.Sandi);
        Masuk = findViewById(R.id.masuk);
        Masuk.setOnClickListener(this);
        Daftar = findViewById(R.id.buatakun);
        Daftar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
