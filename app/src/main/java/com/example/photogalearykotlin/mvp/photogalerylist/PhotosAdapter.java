package com.example.photogalearykotlin.mvp.photogalerylist;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.photogalearykotlin.R;
import com.example.photogalearykotlin.model.album;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.MyViewHolder> {

    private PhotoListActivity photoListActivity;
    private List<album> photoList;

    public PhotosAdapter(PhotoListActivity photoListActivity, List<album> photoList) {
        this.photoListActivity = photoListActivity;
        this.photoList = photoList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        album photoitem = photoList.get(position);

        if(photoitem.getDescription() !=null)
            holder.tvPhotoDescription.setText(photoitem.getDescription());
        else if(photoitem.getAlt_description() !=null)
            holder.tvPhotoDescription.setText(photoitem.getAlt_description());
        holder.tvPhotoUsername.setText(photoitem.getUser().getUsername());

        Glide.with(photoListActivity)
                .load(photoitem.getUrls().getRegular())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.pbLoadImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.pbLoadImage.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(holder.ivPhotoThumb);
/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoListActivity.onPhotoItemClick(position);
            }
        });

 */

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPhotoDescription;
        public TextView tvPhotoUsername;

        public ImageView ivPhotoThumb;

        public ProgressBar pbLoadImage;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvPhotoDescription = itemView.findViewById(R.id.tv_photo_description);
            tvPhotoUsername = itemView.findViewById(R.id.tv_photo_username);
            ivPhotoThumb = itemView.findViewById(R.id.iv_photo_thumb);
            pbLoadImage = itemView.findViewById(R.id.pb_load_image);
        }
    }
}
