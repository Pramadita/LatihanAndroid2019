package com.dita.kurban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LogInPelanggan extends AppCompatActivity implements View.OnClickListener {
    EditText Email, Password;
    TextView Register;
    Button LogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_pelanggan);

        Email = (EditText) findViewById(R.id.edtUname);
        Password = (EditText) findViewById(R.id.edtPW);
        Register = (TextView) findViewById(R.id.BuatAkun);
        LogIn = (Button)  findViewById(R.id.Masuk);

        Register.setOnClickListener(this);
        LogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == Register)
        {
            Intent Register= new Intent(getApplicationContext(), RegisterPelanggan.class);
            startActivity(Register);
        }
        if(view == LogIn)
        {
            Intent LogIn = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(LogIn);
        }
    }
}
