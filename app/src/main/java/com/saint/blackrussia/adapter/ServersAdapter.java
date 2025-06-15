package com.saint.blackrussia.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Build;

import android.view.View.OnClickListener;
import android.graphics.PorterDuff;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Context;

import com.saint.blackrussia.Preferences;
import com.saint.game.R;
import com.saint.blackrussia.model.Servers;
import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.dinuscxj.progressbar.CircleProgressBar;

public class ServersAdapter extends RecyclerView.Adapter<ServersAdapter.ServersViewHolder> {
	Context context;
	
	ArrayList<Servers> slist;
	
	public ServersAdapter(Context context, ArrayList<Servers> slist){
		 this.context = context;
		 this.slist = slist; 
	}
	
	@NonNull
	@Override
    public ServersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.servers_layout_new, parent, false);
		return new ServersViewHolder(v); 
    }

    @Override
    public void onBindViewHolder(@NonNull ServersViewHolder holder, int position) {
        Servers servers = slist.get(position);
        Preferences.setIP(servers.getIP());
        Preferences.setPort(servers.getPORT());
        if(servers.getx2() == false) {
            holder.x2.setVisibility(View.INVISIBLE);
            holder.a.setVisibility(View.INVISIBLE);
            Preferences.setX2(false);
        } else {
            holder.x2.setVisibility(View.VISIBLE);
            holder.a.setVisibility(View.VISIBLE);
            Preferences.setX2(true);
        }
        holder.serverText.setText(servers.getname());
        holder.onlineText.setText(Integer.toString(servers.getOnline()));
        holder.maxOnlineText.setText("/" + Integer.toString(servers.getmaxOnline()));
    }

    @Override
    public int getItemCount() {
        return slist.size();
    }

    public static class ServersViewHolder extends RecyclerView.ViewHolder {

        public TextView onlineText;
        public TextView serverText;
        public TextView maxOnlineText;
        public ImageView x2;
        public ImageView a;
	    
        public ServersViewHolder(View itemView) {
            super(itemView);
            
		    x2 = itemView.findViewById(R.id.brp_launcher_server_double);
            a = itemView.findViewById(R.id.brp_launcher_server_shipping);
            serverText = itemView.findViewById(R.id.brp_launcher_server_title);
            onlineText = itemView.findViewById(R.id.brp_launcher_server_online);
            maxOnlineText = itemView.findViewById(R.id.textView5);
        }
    }
	
}