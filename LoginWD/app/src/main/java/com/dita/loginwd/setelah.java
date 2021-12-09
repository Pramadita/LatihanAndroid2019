package com.dita.loginwd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class setelah extends AppCompatActivity {

    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setelah);

        btnExit = (Button) findViewById(R.id.out);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //perintah untuk mengakhiri aplikasi
                Intent intent = new Intent(setelah.this, MainActivity.class);
                setelah.this.startActivity(intent);
            }
        });
    }
}
