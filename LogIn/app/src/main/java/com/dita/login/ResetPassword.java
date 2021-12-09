package com.dita.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = (EditText) findViewById(R.id.RPEmail);
        btnReset = (Button) findViewById(R.id.ResetPassword2);
        btnReset.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.kembali);
        btnBack.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if(view==btnBack){
            startActivity(new Intent(ResetPassword.this, login.class));
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
