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

public class login extends AppCompatActivity implements View.OnClickListener {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth mAuth;
    private Button btnSignUp, btnLogIn, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth =FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.Email);
        inputPassword =(EditText) findViewById(R.id.Sandi);
        btnSignUp = (Button) findViewById(R.id.buatakun);
        btnSignUp.setOnClickListener(this);
        btnLogIn = (Button) findViewById(R.id.masuk);
        btnLogIn.setOnClickListener(this);
        btnReset = (Button) findViewById (R.id.ResetPassword1);
        btnReset.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        }

    @Override
    public void onClick(View view) {
        if (view == btnSignUp){
            startActivity(new Intent(login.this, signup.class));
        }
        else if(view == btnReset){
            startActivity(new Intent(login.this, ResetPassword.class ));
        }
        else if (view == btnLogIn){
            String email = inputEmail.getText().toString();
            final String password = inputPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Masukkan alamat Email", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(getApplicationContext(),"Masukkan password",Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(!task.isSuccessful()){
                       if(password.length()<5){
                           inputPassword.setError(getString(R.string.minimum_password));
                       }
                       else{
                           Toast.makeText(login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                       }
                   }
                   else{
                       Intent i = new Intent(login.this, MainActivity.class);
                       startActivity(i);
                       finish();
                   }
                }
            });
        }
    }
}

