package com.example.satella.coffeeapps.Animaldetails;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.satella.coffeeapps.R;

import static com.example.satella.coffeeapps.HomeFragment.EXTRA_DESCRIPTION;
import static com.example.satella.coffeeapps.HomeFragment.EXTRA_HARGA;
import static com.example.satella.coffeeapps.HomeFragment.EXTRA_NAME_QURBAN;
import static com.example.satella.coffeeapps.HomeFragment.EXTRA_ID_HEWAN;
import static com.example.satella.coffeeapps.HomeFragment.EXTRA_URL;

public class ContentDetailsActivity extends AppCompatActivity  {


    TextView txtDesc, txtHarga, txtIDhewan, txtName;
    ImageView imgFlag, imgMarker, imgQurban;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hewan_details);

        imgQurban = findViewById(R.id.img_HewanQurban);
        txtName = findViewById(R.id.tv_item_name);
        txtDesc = findViewById(R.id.tv_item_desc);
        txtHarga = findViewById(R.id.tv_Harga);
        txtIDhewan = findViewById(R.id.tv_ID_Hewan);

        imgMarker = findViewById(R.id.marker);

        initView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void initView(){

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String nameQurban = intent.getStringExtra(EXTRA_NAME_QURBAN);
        String desc = intent.getStringExtra(EXTRA_DESCRIPTION);
        String IDHewan = intent.getStringExtra(EXTRA_ID_HEWAN);
        String Harga = intent.getStringExtra(EXTRA_HARGA);

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions())
                .into(imgQurban);



        txtIDhewan.setText(IDHewan);
        txtName.setText(nameQurban);
        txtDesc.setText(desc);
        txtHarga.setText(Harga);

        getSupportActionBar().setTitle(nameQurban);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
