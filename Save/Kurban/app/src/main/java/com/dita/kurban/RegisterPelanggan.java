package com.dita.kurban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterPelanggan extends AppCompatActivity implements View.OnClickListener {
    EditText SuratElektronik, Sandi;
    Button Register;
    TextView LogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pelanggan);

        SuratElektronik = (EditText) findViewById(R.id.edtUsname);
        Sandi = (EditText) findViewById(R.id.edtPSW);
        LogIn= (TextView) findViewById(R.id.MasukAkun);
        Register = (Button) findViewById(R.id.register);

        LogIn.setOnClickListener(this);
        Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == LogIn){
            Intent LogIn = new Intent(getApplicationContext(), LogInPelanggan.class);
            startActivity(LogIn);
        }
        if (view == Register)
        {
            Intent Register = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(Register);
        }
    }
}
