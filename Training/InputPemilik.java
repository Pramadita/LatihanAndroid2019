package com.dita.latihanfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputPemilik extends AppCompatActivity implements View.OnClickListener {
    TextView Name, Address, Phone;
    Button Okey;
    DatabaseReference dbrfc;
    FirebaseDatabase databases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pemilik);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("pemilik/nama");

        myRef.setValue("elsa");
        Name = (TextView) findViewById(R.id.napem1);
        Address = (TextView) findViewById(R.id.al1);
        Phone = (TextView) findViewById(R.id.notel1);
        Okey = (Button) findViewById(R.id.okk);

        databases = FirebaseDatabase.getInstance();
        dbrfc = databases.getReference("pemilik");

        Okey.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //inisialisasi reference firebase
        DatabaseReference nilai1 = dbrfc.push();

        //mengambil nilai dari id xml
        String NameNilai = Name.getText().toString();
        String AddressNilai = Address.getText().toString();
        String PhoneNilai = Phone.getText().toString();

        //menambahkan nilai ke column firebase
        nilai1.child("nama").setValue(NameNilai);
        nilai1.child("alamat").setValue(AddressNilai);
        nilai1.child("no_telp").setValue(Integer.valueOf(PhoneNilai));

        //menampilkan notifikasi toast
        Toast.makeText(getApplicationContext(), "Data Berhasil Ditambahkan !", Toast.LENGTH_SHORT).show();
    }
}
