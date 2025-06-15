package com.saint.blackrussia.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Build;
import android.content.pm.PackageManager;
import android.os.StatFs;

import android.widget.*;
import android.content.pm.ApplicationInfo;
import androidx.core.content.FileProvider;
import android.graphics.PorterDuff;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.Context;
import android.content.ComponentName;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View.OnClickListener;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;

import com.saint.blackrussia.adapter.NewsAdapter;
import com.saint.blackrussia.adapter.ServersAdapter;
import com.saint.blackrussia.model.News;
import com.saint.blackrussia.model.Servers;
import com.saint.blackrussia.other.Lists;
import com.saint.game.R;
import com.saint.blackrussia.activity.SplashActivity;
import com.saint.blackrussia.activity.LoaderActivity;

import java.io.File;

import java.util.*;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    public TextView donate;
    public Button play;
    public Button settings;

    RecyclerView recyclerNews;
    NewsAdapter newsAdapter;
    ArrayList<News> nlist;

    RecyclerView recyclerServers;
    ServersAdapter serversAdapter;
    ArrayList<Servers> slist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_click);
        donate = (TextView) findViewById(R.id.brp_launcher_donate);
        play = (Button) findViewById(R.id.brp_launcher_play);
        settings = (Button) findViewById(R.id.brp_launcher_settings_btn);

        // -------------------------------------------------------------------------
        // Сервера
        recyclerServers = findViewById(R.id.rvServers);
        recyclerServers.setHasFixedSize(true);
        LinearLayoutManager layoutManagerr = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerServers.setLayoutManager(layoutManagerr);

        this.slist = Lists.slist;
        serversAdapter = new ServersAdapter(getApplicationContext(), this.slist);
        recyclerServers.setAdapter(serversAdapter);
        // --------------------------------------------------------------------------
        //          Telegram: @weikton | YouTube: Weikton | VK: @tendensy
        // --------------------------------------------------------------------------
        // Новости
        recyclerNews = findViewById(R.id.rvNews);
        recyclerNews.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerNews.setLayoutManager(layoutManager);

        this.nlist = Lists.nlist;
        newsAdapter = new NewsAdapter(getApplicationContext(), this.nlist);
        recyclerNews.setAdapter(newsAdapter);
        // ---------------------------------------------------------------------------

		settings.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                onClickSettings();
            }
        });

        //donate.setOnClickListener(new OnClickListener() {
        //    public void onClick(View v) {
        //        Toasty.info(getApplicationContext(), "Пополнение счета недоступно", Toast.LENGTH_SHORT, false).show();
        //    }
        //});

        play.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Toasty.info(getApplicationContext(), "https://tapy.me/weikton", Toast.LENGTH_SHORT, false).show();
                startTimer();
            }
        });
    }
	
	public void onClickPlay() {
        if(IsGameInstalled()) {
            startActivity(new Intent(getApplicationContext(), com.saint.game.core.GTASA.class));
		} else {
		    startActivity(new Intent(getApplicationContext(), PreLoadActivity.class));
		}
    }

    public void onClickSettings() {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }

	@SuppressLint("WrongConstant")
    public boolean isRecordAudioPermissionGranted() {
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.RECORD_AUDIO") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 2);
        return false;
    }

    @SuppressLint("WrongConstant")
    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        return false;
    }

    private boolean IsGameInstalled() {
       /* String CheckFile = Environment.getExternalStorageDirectory() + "/Brilliant/texdb/gta3.img";
        File file = new File(CheckFile);
        return file.exists();*/
        String path = Environment.getExternalStorageDirectory() + "/Brilliant/";
        if(!(new File(path + "texdb/gta3.img").exists()))
            return false;
        if(!(new File(path + "anim/anim.img").exists()))
            return false;
        if(!(new File(path + "audio/SFX/FEET.osw").exists()))
            return false;
        if(!(new File(path + "data/plants.dat").exists()))
            return false;
        if(!(new File(path + "data/gta.dat").exists()))
            return false;
        if(!(new File(path + "data/fonts.dat").exists()))
            return false;
        if(!(new File(path + "data/water.dat").exists()))
            return false;
        if(!(new File(path + "data/paths/carrec.img").exists()))
            return false;
        if(!(new File(path + "models/effects.fxp").exists()))
            return false;
        if(!(new File(path + "SAMP/vehicles.ide").exists()))
            return false;
        if(!(new File(path + "SAMP/gta.dat").exists()))
            return false;
        if(!(new File(path + "texdb/samp.img").exists()))
            return false;
        if(!(new File(path + "texdb/samp/samp.txt").exists()))
            return false;
        if(!(new File(path + "texdb/gta3/gta3.txt").exists()))
            return false;
        if(!(new File(path + "texdb/player/player.txt").exists()))
            return false;
        if(!(new File(path + "texdb/gta_int/gta_int.txt").exists()))
            return false;
        return true;
    }
	
	private void startTimer() {
        Timer t = new Timer();
        t.schedule(new TimerTask(){

            @Override
            public void run() {
                onClickPlay();
            }
        }, 100L);
    }
	
	public void onDestroy() {
        super.onDestroy();
    }

    public void onRestart() {
        super.onRestart();
    }
} 