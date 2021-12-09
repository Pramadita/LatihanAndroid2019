package com.example.satella.coffeeapps.Fooddetails;

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
import static com.example.satella.coffeeapps.HomeFragment.EXTRA_FROM;
import static com.example.satella.coffeeapps.HomeFragment.EXTRA_NAME_FOOD;
import static com.example.satella.coffeeapps.HomeFragment.EXTRA_PRICE;
import static com.example.satella.coffeeapps.HomeFragment.EXTRA_URL;

public class ContentDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    TextView txtName, txtDesc, txtFrom, txtPrice;
    ImageView imgFood, imgFlag, imgMarker;

    String goolgeMap = "com.google.android.apps.maps"; // identitas package aplikasi google masps android
    Uri gmmIntentUri;
    Intent mapIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        imgFood = findViewById(R.id.img_food);
        txtName = findViewById(R.id.tv_item_name);
        txtDesc = findViewById(R.id.tv_item_desc);
        txtFrom = findViewById(R.id.tv_from);
        txtPrice = findViewById(R.id.tv_price);

        imgMarker = findViewById(R.id.marker);
        imgMarker.setOnClickListener(this);

        initView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void initView(){

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String nameFood = intent.getStringExtra(EXTRA_NAME_FOOD);
        String desc = intent.getStringExtra(EXTRA_DESCRIPTION);
        String price = intent.getStringExtra(EXTRA_PRICE);
        String from = intent.getStringExtra(EXTRA_FROM);

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions())
                .into(imgFood);



        txtPrice.setText(price);
        txtName.setText(nameFood);
        txtDesc.setText(desc);
        txtFrom.setText(from);

        getSupportActionBar().setTitle(nameFood);

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

    @Override
    public void onClick(View v) {
        gmmIntentUri = Uri.parse("google.navigation:q=" + txtPrice.getText().toString());

        // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
        mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
        mapIntent.setPackage(goolgeMap);

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
