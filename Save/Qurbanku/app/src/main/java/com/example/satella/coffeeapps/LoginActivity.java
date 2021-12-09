package com.example.satella.coffeeapps;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth mAuth;
    private Button btnLogIn;
    private TextView BuatAkun, ResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth =FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword =(EditText) findViewById(R.id.sandi);
        btnLogIn = (Button) findViewById(R.id.masuk);
        btnLogIn.setOnClickListener(this);
        BuatAkun = (TextView) findViewById(R.id.BuatAkun);
        BuatAkun.setOnClickListener(this);
        ResetPassword = (TextView) findViewById(R.id.ResetPassword1);
        ResetPassword.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view == BuatAkun){
            startActivity(new Intent(LoginActivity.this, SignUp.class));
        }
        else if(view == ResetPassword){
            startActivity(new Intent(LoginActivity.this, ResetPassword.class ));
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
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        if(password.length()<5){
                            inputPassword.setError(getString(R.string.minimum_password));
                        }
                        else{
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            });
        }
    }
    public boolean doubleTapParam = false;
    @Override
    public void onBackPressed() {
        if (doubleTapParam){
            super.onBackPressed();
            return;
        }
        this.doubleTapParam = true;
        Toast.makeText(this,"Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleTapParam = false;
            }
        }, 2000);

    }

}
