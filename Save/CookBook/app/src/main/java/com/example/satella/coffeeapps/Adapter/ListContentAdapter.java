package com.example.satella.coffeeapps.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.satella.coffeeapps.Model.Content;
import com.example.satella.coffeeapps.R;

import java.util.ArrayList;

public class ListContentAdapter extends RecyclerView.Adapter<ListContentAdapter.ListViewHolder> {

    private ArrayList<Content> listContent;

    public ListContentAdapter(ArrayList<Content> list) {
        this.listContent = list;
    }

    private OnItemClickCallkback onItemClickCallkback;

    public void setOnItemClickCallkback(OnItemClickCallkback onItemClickCallkback) {
        this.onItemClickCallkback = onItemClickCallkback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Content content = listContent.get(position);

        Glide.with(holder.itemView.getContext())
                .load(content.getPhoto())
                .apply(new RequestOptions().centerInside())
                .into(holder.imgPhoto);

        holder.tvName.setText(content.getName());
        holder.tvFrom.setText(content.getFrom());
        holder.tvDesc.setText(content.getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallkback.onItemClicked(listContent.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listContent.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom, tvprice, tvDesc;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);

        }
    }

    public interface OnItemClickCallkback {
        void onItemClicked(Content data);
    }

}
