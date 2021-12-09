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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPw;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        btnSignIn = (Button) findViewById(R.id.SignIn);
        btnSignIn.setOnClickListener(this);
        btnSignUp = (Button) findViewById(R.id.SignUp);
        btnSignUp.setOnClickListener(this);
        inputEmail = (EditText) findViewById(R.id.brEmail);
        inputPassword = (EditText) findViewById(R.id.brSandi);
        btnResetPw = (Button) findViewById(R.id.ResetPassword);
        btnResetPw.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnResetPw) {
            Intent i = new Intent(getApplicationContext(), ResetPassword.class);
            startActivity(i);
        } else if (view == btnSignIn) {
            Intent i = new Intent(getApplicationContext(), login.class);
            startActivity(i);
        } else if (view == btnSignUp) {
            String email = inputEmail.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Masukkan alamat email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Masukkan password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length() < 5) {
                Toast.makeText(getApplicationContext(), "Password terlalu pendek, masukkan minimal 5 karakter", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(signup.this, "Complete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                    if (!task.isSuccessful()) {
                        Toast.makeText(signup.this, "Autentifikasi Gagal" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(signup.this, MainActivity.class));
                        finish();
                    }
                }
            });
        }
    }
}


