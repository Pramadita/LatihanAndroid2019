package com.dita.log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;

public class sign_up extends AppCompatActivity {

    private EditText User, NoTelp, Sandi, VrfSandi;
    private Button Daftar;
    private TextView SignIn ;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener stateListener;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String VerifikasiID;
    private String No_Telepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //Meneteksi Apakah Ada User Yang Sedang Login (Belum Logout)
                if(user != null){
                    //Jika Ada, User Tidak perlu Login Lagi, dan Langsung Menuju Acivity Yang Dituju
                    startActivity(new Intent(login.this, main_menu.class));
                    finish();
                }
            }
        };

        Daftar = (Button) findViewById(R.id.daftar);
        SignIn = (TextView) findViewById(R.id.SignIn);
        User = (EditText) findViewById(R.id.brNamaPg);
        NoTelp = (EditText) findViewById(R.id.brNoTlp);
        Sandi = (EditText) findViewById(R.id.brSandi);
        VrfSandi = (EditText) findViewById(R.id.vrfSandi);

        //Variable yang Dibutuhkan Untuk Autentikasi





    }
}
