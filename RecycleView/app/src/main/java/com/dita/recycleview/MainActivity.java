package com.dita.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String URL_JSON = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest ArrayRequest ;
    private RequestQueue requestQueue ;
    private List<Anime> lstAnime = new ArrayList<>();
    private RecyclerView myrv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myrv = findViewById(R.id.rv);
        jsoncall();
    }

}

    public void jsoncall() {


        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;


                for (int i = 0 ; i<response.length();i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();

                        anime.setName(jsonObject.getString("name"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setImage_url(jsonObject.getString("img"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                        lstAnime.add(anime);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                Toast.makeText(MainActivity.this,"Size of Liste "+String.valueOf(lstAnime.size()),Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,lstAnime.get(1).toString(),Toast.LENGTH_SHORT).show();

                setRvadapter(lstAnime);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(ArrayRequest);
    }



    public void setRvadapter (List<jalur> lst) {

        RvAdapter myAdapter = new RvAdapter(this,lst) ;
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
}
}
