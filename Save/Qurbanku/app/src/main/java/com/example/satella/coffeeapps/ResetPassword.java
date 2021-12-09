package com.example.satella.coffeeapps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEmail;
    private Button btnReset;
    private FirebaseAuth mAuth;
    private TextView Kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        inputEmail = (EditText) findViewById(R.id.email2);
        btnReset = (Button) findViewById(R.id.ResetPassword3);
        btnReset.setOnClickListener(this);
        Kembali = (TextView) findViewById(R.id.back);
        Kembali.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if(view==Kembali){
            startActivity(new Intent(ResetPassword.this, LoginActivity.class));
        }
        if(view == btnReset){
            String email = inputEmail.getText().toString().trim();
            if(TextUtils.isEmpty(email)){
                Toast.makeText(getApplication(),"Masukkan email yang teregistrasi", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetPassword.this, "Kami telah mengirimkan instruksi untuk mengatur ulang password anda", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ResetPassword.this, "Gagal mengirim email", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
