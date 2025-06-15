package com.saint.blackrussia.activity;
// --------------------------------------------------------------------------
//          Telegram: @weikton | YouTube: Weikton | VK: @tendensy
// --------------------------------------------------------------------------

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saint.game.R;

import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class SettingsActivity extends AppCompatActivity {
    public Button back;
    public Button brp_launcher_reinstall;
    public EditText nickname;
    public ImageView imageViewTelegram;
    public ImageView imageViewVK;
    public ImageView imageViewDiscord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
		
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_click);
        back = (Button) findViewById(R.id.brp_launcher_settings_back);
        brp_launcher_reinstall = (Button) findViewById(R.id.brp_launcher_reinstall);
        nickname = (EditText) findViewById(R.id.brp_launcher_settings_nick);
        imageViewTelegram = (ImageView) findViewById(R.id.imageViewTelegram);
        imageViewVK = (ImageView) findViewById(R.id.imageViewVK);
        imageViewDiscord = (ImageView) findViewById(R.id.imageViewDiscord);

        Load();
        nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                        Wini ws = new Wini(new File(Environment.getExternalStorageDirectory() + "/Brilliant/SAMP/settings.ini"));
                        ws.put("client", "name", nickname.getText());
                        ws.store();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //v.startAnimation(animation);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        brp_launcher_reinstall.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(animation);
                startActivity(new Intent(getApplicationContext(), PreLoadActivity.class));
                finish();
            }
        });

        imageViewTelegram.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(animation);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/reytiz"));
                startActivity(browserIntent);
            }
        });
        imageViewVK.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(animation);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/reytiz"));
                startActivity(browserIntent);
            }
        });
        imageViewDiscord.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(animation);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.com/"));
                startActivity(browserIntent);
            }
        });

        Toasty.info(this, "Никнейм сохраняется автоматически", Toast.LENGTH_SHORT, false).show();
    }
    private void Load() {
        try {
            Wini w = new Wini(new File(Environment.getExternalStorageDirectory() + "/Brilliant/SAMP/settings.ini"));
            nickname.setText(w.get("client", "name"));
            w.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 