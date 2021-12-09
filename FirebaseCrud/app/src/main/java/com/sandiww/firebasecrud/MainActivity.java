package com.sandiww.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sandiww.firebasecrud.models.data_mahasiswa;

import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Deklarasi Variable
    private ProgressBar progressBar;
    private EditText NIM, Nama, Jurusan;
    private FirebaseAuth auth;
    private Button Login, Save, Logout, Show;
    private String getNIM, getNama, getJurusan;

    // Variable untuk menyimpan ID dari User yang Ter-Authetifikasi
    private String getUserID;

    // Membuat Kode Permintaan
    private int RC_SIGN_IN = 1;

    private FirebaseDatabase database;
    private DatabaseReference getReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);

        // Inisialisasi ID Button
        Login = (Button)findViewById(R.id.buttonLogin);
        Login.setOnClickListener(this);

        Save = (Button)findViewById(R.id.buttonSave);
        Save.setOnClickListener(this);

        Logout = (Button)findViewById(R.id.buttonLogout);
        Logout.setOnClickListener(this);

        Show = (Button)findViewById(R.id.buttonShowData);
        Show.setOnClickListener(this);

        database = FirebaseDatabase.getInstance(); // Mendapatkan Instance Firebase Realtime Database
        auth = FirebaseAuth.getInstance(); // Menghubungkan dengan Firebase Authentifikasi

        // Inisialisasi ID EditText
        NIM = (EditText)findViewById(R.id.editNim);
        Nama = (EditText)findViewById(R.id.editNama);
        Jurusan =(EditText)findViewById(R.id.editJurusan);

        /*
         * Mendeteksi apakah ada user yang masuk, Jika tidak, maka setiap komponen UI akan dinonaktifkan
         * Kecuali Tombol Login. Dan jika ada user yang terautentikasi, semua fungsi/komponen
         * didalam User Interface dapat digunakan, kecuali tombol Logout
         */

        if (auth.getCurrentUser() == null){
            Login.setEnabled(true);
            Save.setEnabled(false);
            Logout.setEnabled(false);
            Show.setEnabled(false);
            NIM.setEnabled(false);
            Nama.setEnabled(false);
            Jurusan.setEnabled(false);
        }else{
            updateUI();
        }

    }

    // Tampilkan default pada Activity jika user belum Ter-auntentifikasi
    private void updateUI() {
        Login.setEnabled(false);
        Save.setEnabled(true);
        Logout.setEnabled(true);
        Show.setEnabled(true);
        NIM.setEnabled(true);
        Nama.setEnabled(true);
        Jurusan.setEnabled(true);
        progressBar.setVisibility(View.GONE);

        // Mendapatkan User ID dari Akun Google Yang Ter-Authetifikasi
        getUserID = auth.getCurrentUser().getUid();
    }

    // Mengecek apakah data yang kosong, akan digunakan pada Tutorial Ini
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN adalaha kode permintaan yang Anda berikan ke startActivityForResult, saat memulai masuknya arus.
        if (requestCode == RC_SIGN_IN){
            // Berhasil Masuk
            if (resultCode == RESULT_OK){
                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                updateUI();
            }else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLogin:
                // Statement program untuk Login
                startActivityForResult(AuthUI.getInstance().
                        createSignInIntentBuilder()
                // Memilih Provider atau Methode yang akan digunakan
                        .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false).build(), RC_SIGN_IN);
                progressBar.setVisibility(View.VISIBLE);
                break;

            case R.id.buttonSave:
                // Statement program untuk menyimpan Data
                getNIM = NIM.getText().toString();
                getNama = Nama.getText().toString();
                getJurusan = Jurusan.getText().toString();
                getReference = database.getReference(); // Mendapatkan Data Referensi Dari Database
                // Mendapatkan UserID dari pengguna yang Ter-Autentifikasi
                String getUserID  = auth.getCurrentUser().getUid();

                // Mendapatkan Instance dari database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                //Menyimpan data yang diinputkan User ke dalam Variable
                String getNIM = NIM.getText().toString();
                String getNama = Nama.getText().toString();
                String getJurusan = Jurusan.getText().toString();

                getReference = database.getReference(); //Mendapatkan Referensi Dari Database

                // Mengecek apakah ada data yang kosong
                if (isEmpty(getNIM) && isEmpty(getNama) && isEmpty(getJurusan)){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(MainActivity.this, "Data tidak boleh kosong.", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                    Jika Tidak, maka data dapat diproses dan meyimpannya pada Database
                    Menyimpan data referensi pada Database berdasarkan User ID dari masing-masing Akun
                    */
                    getReference.child("Admin").child(getUserID).child("Mahasiswa").push()
                    .setValue(new data_mahasiswa(getNIM, getNama, getJurusan))
                    .addOnSuccessListener(this, new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                            NIM.setText("");
                            Nama.setText("");
                            Jurusan.setText("");
                            Toast.makeText(MainActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                break;

            case R.id.buttonLogout:
                // Statement program Logout
                AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Toast.makeText(MainActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
            case R.id.buttonShowData:
                // Digunakan untuk melihat data yang sudah tersimpan didalam Database
                // Akan digunakan pada Tutorial Berikutnya, mengenai penggunaan Fungsi Read
                break;
        }
    }
}
