package com.agung.latihanjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView namasekolah;
    private String nama,mapel;
    private ArrayList<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listnama = findViewById(R.id.list);
        data = new ArrayList<>();

        namasekolah = (TextView) findViewById(R.id.namasekolah);
        ParsingJson();

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, data);
        listnama.setAdapter(adapter);
    }

    private void ParsingJson(){
        String json = FileUtil.copyFromAssets(this.getApplicationContext(),"datasekolah.json");

        try{
            JSONObject jsonobj = new JSONObject(json);
            JSONArray dataguru = jsonobj.getJSONArray("dataguru");

            String objnamasekolah = jsonobj.getString(SekolahTags.TAG_SEKOLAH);

            namasekolah.setText(objnamasekolah);
            int a=0;
            for(int i =0; i<dataguru.length(); i++){
                JSONObject jsonobject = dataguru.getJSONObject(i);
                nama = jsonobject.getString("nama");
                mapel = jsonobject.getString("matapelajaran");
                a++;
                Collections.addAll(data,"No."+a+" Nama : "+nama+" Guru "+"Mata Pelajaran : "+mapel);
            }


        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
