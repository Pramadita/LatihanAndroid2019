package com.example.satella.coffeeapps;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.satella.coffeeapps.Adapter.ContentData;
import com.example.satella.coffeeapps.Adapter.ListContentAdapter;
import com.example.satella.coffeeapps.Fooddetails.ContentDetailsActivity;
import com.example.satella.coffeeapps.Model.Content;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME_FOOD = "nameFood";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_FLAG = "flag";
    public static final String EXTRA_FROM = "fromFood";

    private RecyclerView rvFood;
    private ArrayList<Content> list = new ArrayList<>();

    TextView tvName, tvDesc;
    ImageView imgFood, btnExit;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvFood = view.findViewById(R.id.rv_food);
        rvFood.setHasFixedSize(true);

        imgFood = view.findViewById(R.id.img_food);
        tvName = view.findViewById(R.id.tv_item_name);
        tvDesc = view.findViewById(R.id.tv_item_desc);

        btnExit = view.findViewById(R.id.exit);
        btnExit.setOnClickListener(this);


        list.addAll(ContentData.getListData());
        showRecyclerList();

        return view;

    }
    private void showSelectedHero(Content content){
       Toast.makeText(getContext(),"memilih"+ content.getName(),Toast.LENGTH_SHORT).show();
    }

    private void showRecyclerList(){
        rvFood.setLayoutManager(new LinearLayoutManager(getContext()));
        ListContentAdapter listContentAdapter = new ListContentAdapter(list);
        rvFood.setAdapter(listContentAdapter);

        listContentAdapter.setOnItemClickCallkback(new ListContentAdapter.OnItemClickCallkback() {


            @Override
            public void onItemClicked(Content data) {

                Intent intent = new Intent(getContext(), ContentDetailsActivity.class);

                intent.putExtra(EXTRA_NAME_FOOD, data.getName());
                intent.putExtra(EXTRA_DESCRIPTION, data.getDesc());
                intent.putExtra(EXTRA_URL, data.getPhoto());
                intent.putExtra(EXTRA_PRICE, data.getPrice());
                intent.putExtra(EXTRA_FLAG, data.getFlag());
                intent.putExtra(EXTRA_FROM, data.getFrom());

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), getView(),"sharedName");

                startActivity(intent, options.toBundle());
            }
        });

    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getContext().getApplicationContext(), LoginActivity.class);
        HomeFragment.this.startActivity(intent);
    }
}
