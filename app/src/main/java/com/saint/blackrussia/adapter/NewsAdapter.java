package com.saint.blackrussia.adapter;

import android.content.Intent;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.saint.blackrussia.model.News;
import com.saint.game.R;
import java.util.ArrayList;

import com.bumptech.glide.Glide;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
	Context context;
	
	ArrayList<News> nlist;
	
	public NewsAdapter(Context context, ArrayList<News> nlist){
		 this.context = context;
		 this.nlist = nlist; 
	}
	
	@NonNull
	@Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
		return new NewsViewHolder(v); 
    }
  
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = nlist.get(position);
		holder.title.setText(news.getTitle());
		Glide.with(context).load(news.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return nlist.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        
		TextView title;
		ImageView image;

        public NewsViewHolder(View itemView) {
            super(itemView);
            
		    title = itemView.findViewById(R.id.tvNewsText);
			image = itemView.findViewById(R.id.ivNewsImage);
        }
    }
}