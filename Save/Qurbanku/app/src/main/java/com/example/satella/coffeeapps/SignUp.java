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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText inputEmail, inputPassword;
    private Button btnSignUp;
    private TextView LogIn, LupaPw;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

        LogIn = (TextView) findViewById(R.id.MasukAkun);
        LogIn.setOnClickListener(this);
        btnSignUp = (Button) findViewById(R.id.masukbuat);
        btnSignUp.setOnClickListener(this);
        inputEmail = (EditText) findViewById(R.id.email1);
        inputPassword = (EditText) findViewById(R.id.sandi1);
        LupaPw = (TextView) findViewById(R.id.ResetPassword);
        LupaPw.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == LupaPw) {
            Intent i = new Intent(getApplicationContext(), ResetPassword.class);
            startActivity(i);
        } else if (view == LogIn) {
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
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
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(SignUp.this, "Complete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "Autentifikasi Gagal" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(SignUp.this, MainActivity.class));
                        finish();
                    }
                }
            });
        }

    }
}

