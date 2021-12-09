package com.dita.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail, btnRemoveUser, changeEmail, changePassword, sendEmail, remove, signout;
    private EditText oldEmail, newEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    startActivity(new Intent( MainActivity.this, login.class));
                    finish();
                }
            }
        };

        /*btnChangeEmail = (Button) findViewById(R.id.ChangeEmailButton);
        btnChangeEmail.setOnClickListener(this);
        btnChangePassword = (Button) findViewById(R.id.cjangepasswordbutton);
        btnChangePassword.setOnClickListener(this);
        btnSendResetEmail = (Button) findViewById(R.id.sendpwresetbutton);
        btnSendResetEmail.setOnClickListener(this);
        btnRemoveUser = (Button) findViewById(R.id.removeuserbutton);*/
        changeEmail = (Button) findViewById(R.id.ChangeEmail);
        changeEmail.setOnClickListener(this);
        changePassword = (Button) findViewById(R.id.ChangePass) ;
        changePassword.setOnClickListener(this);
        sendEmail = (Button) findViewById(R.id.send);
        sendEmail.setOnClickListener(this);
        /*remove = (Button) findViewById(R.id.remove);
        remove.setOnClickListener(this);*/
        signout = (Button) findViewById(R.id.SignOut);
        signout.setOnClickListener(this);

        oldEmail= (EditText) findViewById (R.id.oldemail);
        newEmail= (EditText) findViewById (R.id.newemail);
        password =(EditText) findViewById (R.id.password);
        newPassword= (EditText) findViewById (R.id.newpassword);



        progressBar = (ProgressBar) findViewById (R.id.progressbar) ;
        if(progressBar !=null){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    startActivity(new Intent( MainActivity.this, login.class));
                    finish();
                }
            }
        };
        /*if(view==btnChangeEmail){
            oldEmail.setVisibility(View.GONE);
            newEmail.setVisibility(View. GONE);
            password.setVisibility (View.GONE);
            newPassword.setVisibility(View.GONE);
            changeEmail.setVisibility (View.GONE) ;
            changePassword.setVisibility (View. GONE);
            sendEmail.setVisibility (View.GONE);
            remove.setVisibility (View. GONE) ;
        }*/

        if(view==changeEmail){
            mAuth = FirebaseAuth.getInstance();

            if(user != null && !newEmail.getText().toString().trim().equals("")){
                user.updateEmail(newEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Alamat email diperbarui, silakan masuk dnegan emal baru", Toast.LENGTH_SHORT).show();
                            signOut();
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Gagal Update", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
            else if (newEmail.getText().toString().trim().equals("")){
                newEmail.setError("Masukkan Email");
                progressBar.setVisibility(View.GONE);
            }
        }

       /* else if(view==btnChangePassword) {
            oldEmail.setVisibility(View.GONE);
            newEmail.setVisibility(View. GONE);
            password.setVisibility (View.GONE);
            newPassword.setVisibility(View.GONE);
            changeEmail.setVisibility (View.GONE) ;
            changePassword.setVisibility (View. GONE);
            sendEmail.setVisibility (View.GONE);
            remove.setVisibility (View. GONE) ;
        }*/

        else  if(view==changePassword){
            progressBar.setVisibility(View.GONE);
            if(user != null && !newPassword.getText().toString().trim().equals("")){
                if(newPassword.getText().toString().trim().length()<5){
                    newPassword.setError("Password Terlalu Pendek, masukkan minimal 5 karakter");
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    user.updatePassword(newPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Passwor diperbarui, silakan masuk dengan password baru",Toast.LENGTH_SHORT).show();
                                signOut();
                                progressBar.setVisibility(View.GONE);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Gagal memperbarui password",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
            else if(newPassword.getText().toString().trim().equals("")){
                newPassword.setError("Masukkan password");
                progressBar.setVisibility(View.GONE);
            }
        }

        /*else if(view==btnSendResetEmail){
            oldEmail.setVisibility(View.GONE);
            newEmail.setVisibility(View. GONE);
            password.setVisibility (View.GONE);
            newPassword.setVisibility(View.GONE);
            changeEmail.setVisibility (View.GONE) ;
            changePassword.setVisibility (View. GONE);
            sendEmail.setVisibility (View.GONE);
            remove.setVisibility (View. GONE) ;
        }*/

        else if(view==sendEmail){
            progressBar.setVisibility(View.GONE);
            if(!oldEmail.getText().toString().trim().equals("")){
                mAuth.sendPasswordResetEmail(oldEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Reset Password Email Terkirim", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Gagal mengirim reset email", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
            else{
                oldEmail.setError("Masukkan Email");
                progressBar.setVisibility(View.GONE);
            }
        }

        /*else if(view==btnRemoveUser){
            if(user != null){
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Akun Terhapus, Silakan buat akun baru", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, signup.class));
                            finish();
                            progressBar.setVisibility(View.GONE);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Gagal hapus akun", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        }*/

        else if(view==signout){
            signOut();
        }

    }
    public void signOut(){
        mAuth.signOut();
    }
    protected void onResume(){
        super.onResume();
    }
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(authListener);
    }
    public void onStop(){
        super.onStop();
        if(authListener != null){
            mAuth.removeAuthStateListener(authListener);
        }
    }
}
