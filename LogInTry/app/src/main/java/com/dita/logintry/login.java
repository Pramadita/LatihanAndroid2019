package com.dita.logintry;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity implements OnClickListener{

    //Variable Untuk Komponen-komponen Yang Diperlukan
    private EditText NoTelepon, SetKode;
    private Button Masuk, Verifikasi, Resend, Daftar;
    private TextView PhoneID;

    //Variable yang Dibutuhkan Untuk Autentikasi
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener stateListener;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String VerifikasiID;
    private String No_Telepon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneID = findViewById(R.id.no_id);
        NoTelepon = findViewById(R.id.phone);
        SetKode = findViewById(R.id.setVertifi);
        Masuk = findViewById(R.id.login);
        Verifikasi = findViewById(R.id.verifi);
        Resend = findViewById(R.id.resend);
        Daftar = findViewById(R.id.Daftar);


        Resend.setOnClickListener(this);
        Resend.setEnabled(false);
        Masuk.setOnClickListener(this);
        Verifikasi.setOnClickListener(this);
        Daftar.setOnClickListener(this);

        //Menghubungkan Project Dengan Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //Meneteksi Apakah Ada User Yang Sedang Login (Belum Logout)
                if(user != null){
                    //Jika Ada, User Tidak perlu Login Lagi, dan Langsung Menuju Acivity Yang Dituju
                    startActivity(new Intent(login.this, MainActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Melampirkan Listener pada FirebaseAuth saat Activity Dimulai
        mAuth.addAuthStateListener(stateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(stateListener != null){
            //Menghapus Listener pada FirebaseAuth saat Activity Dihentikan
            mAuth.removeAuthStateListener(stateListener);
        }
    }

    private void setupVerificationCallback(){
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                // Callback didalam sini akan dipanggil/dieksekusi saat terjadi proses pengiriman kode
                // Dan User Diminta untuk memasukan kode verifikasi

                // Untuk Menyimpan ID verifikasi dan kirim ulang token
                VerifikasiID = verificationId;
                resendToken = token;
                Resend.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Mendapatkan Kode Verifikasi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential Credential) {
                Toast.makeText(getApplicationContext(), "Verifikasi Selesai", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(Credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(), "Verifikasi Gagal, Silakan Coba Lagi", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Sign In Berhasil
                            startActivity(new Intent(login.this, MainActivity.class));
                            finish();
                        }
                        else{
                            //Sign In Gagal
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                // Kode Yang Dimasukan tidal Valid.
                                Toast.makeText(getApplicationContext(), "Kode yang dimasukkan tidak valid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                No_Telepon = PhoneID.getText()+NoTelepon.getText().toString();
                setupVerificationCallback();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        No_Telepon, //NO Telepon Untuk Di Verifikasi
                        60, //Durasi Waktu Habis
                        TimeUnit.SECONDS, //Unit Timeout
                        this, //Activity
                        callbacks); // OnVerificationStateChangedCallbacks
                Toast.makeText(getApplicationContext(), "Memverifikasi, Mohon Tunggu", Toast.LENGTH_SHORT).show();
                NoTelepon.setText("");
                break;

            case R.id.verifi:
                String verifiCode = SetKode.getText().toString();
                if(TextUtils.isEmpty(verifiCode)){
                    Toast.makeText(getApplicationContext(),"Masukan Kode Verifikasi", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Memverifikasi Nomor Telepon, Saat Tombol Verifikasi Ditekan
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerifikasiID, verifiCode);
                    signInWithPhoneAuthCredential(credential);
                    Toast.makeText(getApplicationContext(),"Sedang Memverifikasi", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.resend:
                No_Telepon = PhoneID.getText()+NoTelepon.getText().toString();
                setupVerificationCallback();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        No_Telepon, //NO Telepon Untuk Di Vertifikai
                        60, //Durasi Waktu Habis
                        TimeUnit.SECONDS, //Unit Timeout
                        this, //Activity
                        callbacks, // OnVerificationStateChangedCallbacks
                        resendToken); // Digunakan untuk mengirim ulang kembali kode verifikasi
                Toast.makeText(getApplicationContext(), "Mengirim Ulang Kode Verifikasi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Daftar :
                startActivity(new Intent(login.this, register.class));
        }
    }
}