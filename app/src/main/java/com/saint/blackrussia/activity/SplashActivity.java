package com.saint.blackrussia.activity;
// --------------------------------------------------------------------------
//          Telegram: @weikton | YouTube: Weikton | VK: @tendensy
// --------------------------------------------------------------------------

import android.Manifest;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.saint.blackrussia.Preferences;
import com.saint.blackrussia.model.News;
import com.saint.blackrussia.other.Interface;
import com.saint.game.R;

import java.util.Timer;
import java.util.TimerTask;

import java.util.ArrayList;
import java.util.List;

import com.saint.game.R;
import com.saint.blackrussia.adapter.ServersAdapter;
import com.saint.blackrussia.model.Servers;
import com.saint.blackrussia.other.Lists;
import com.liulishuo.filedownloader.FileDownloader;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity{
    public static ArrayList<Servers> slist;
    public ConstraintLayout notNetwork;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        notNetwork = (ConstraintLayout) findViewById(R.id.brp_launcher_not_internet);

        Lists.slist = new ArrayList<>();
        //Lists.slist.add(new Servers("true", "Cullinan", 2, 500));

        Lists.slist = new ArrayList<>();
        Lists.nlist = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://vbd.fdv.dd/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interface sInterface = retrofit.create(Interface.class);

        Call<List<Servers>> scall = sInterface.getServers();

        scall.enqueue(new Callback<List<Servers>>() {
            @Override
            public void onResponse(Call<List<Servers>> call, Response<List<Servers>> response) {

                List<Servers> servers = response.body();

                for (Servers server : servers) {
                    Lists.slist.add(new Servers(server.getIP(), server.getPORT(), server.getx2(), server.getname(), server.getOnline(), server.getmaxOnline()));
                }
            }

            @Override
            public void onFailure(Call<List<Servers>> call, Throwable t) {
                Toasty.error(getApplicationContext(), "Не удалось получить файл servers.json", Toast.LENGTH_SHORT, false).show();
            }
        });

        Call<List<News>> ncall = sInterface.getNews();

        ncall.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                List<News> news = response.body();

                for (News storie : news) {
                    Lists.nlist.add(new News(storie.getImageUrl(), storie.getTitle()));
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Toasty.error(getApplicationContext(), "Не удалось получить файл news.json", Toast.LENGTH_SHORT, false).show();
            }
        });

        if (isOnline(this)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                        || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
                } else {
                    startTimer();
                }
            } else startTimer();
        } else showNetworkProblem();
    }
	
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            startLauncher();
        }
    }

    private void startLauncher()
    {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void showNetworkProblem()
    {
        notNetwork.setVisibility(View.VISIBLE);
        killLauncher();
    }
	
	public static boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }

    private void killLauncher()
    {
        Timer t = new Timer();
        t.schedule(new TimerTask(){
            @Override
            public void run() {
                onDestroy();
            }
        }, 10000L);
    }
	
	private void startTimer()
    {
        Timer t = new Timer();
        t.schedule(new TimerTask(){
            @Override
            public void run() {
                startLauncher();
            }
        }, 1000L);
    }
}