package com.vainius.augustinas.androidvisma;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mCtx;
    private JSONArray imageList;

    public ImageAdapter(Context mCtx, JSONArray imageList) {
        this.mCtx = mCtx;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        //XMLas i View
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
     //paveiksliukas-->ImageView
        ImageLoader imageLoader = ImageLoader.getInstance();
        try {
            imageLoader.displayImage(imageList.getString(position), holder.imageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //paspaudimo ant paveiksliuko atveju, siunciam intenta naujam langui(activiciui)
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) throws JSONException {
                Intent intent= new Intent(mCtx, SingleImageWindow.class);
                intent.putExtra("image_url", imageList.getString(position));
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.length();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        private ItemClickListener itemClickListener;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            try {
                itemClickListener.onClick(view, getAdapterPosition());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
