package com.saint.game.gui;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.saint.game.R;
import com.saint.game.gui.util.Utils;

public class Welcome {
    public Activity activity;
    public Animation animation;

    public ConstraintLayout constraintLayout;
    public Button mPlay;

    public TextView mTitle;
    public TextView mDescription;

    public Welcome(Activity aactivity){
        constraintLayout = aactivity.findViewById(R.id.brp_welcome_main);
        animation = AnimationUtils.loadAnimation(aactivity, R.anim.button_click);

        mTitle = aactivity.findViewById(R.id.brp_welcome_title);
        mDescription = aactivity.findViewById(R.id.brp_welcome_desc);

        mPlay = aactivity.findViewById(R.id.brp_welcome_btn);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide(view);
            }
        });

        mTitle.animate().setDuration(0).translationXBy(-2000.0f).start();
        mDescription.animate().setDuration(0).translationXBy(-2000.0f).start();
        mPlay.animate().setDuration(0).translationXBy(-2000.0f).start();

        Utils.HideLayout(constraintLayout, false);

    }

    public void show(boolean isRegister) {
        Utils.ShowLayout(constraintLayout, true);
        if (isRegister) {
            mTitle.setText("Добро пожаловать\nНА TEST SERVER");
        } else {
            mTitle.setText("С возвращением\nНА TEST SERVER");
        }
        mTitle.animate().setDuration(1500).translationXBy(2000.0f).start();
        mDescription.animate().setDuration(1500).setStartDelay(250).translationXBy(2000.0f).start();
        mPlay.animate().setDuration(1500).setStartDelay(500).translationXBy(2000.0f).start();
    }

    public void hide(View v) {
        Utils.HideLayout(constraintLayout, true);
        v.startAnimation(animation);
        mPlay.animate().setDuration(1500).translationXBy(-2000.0f).start();
        mDescription.animate().setDuration(1500).setStartDelay(250).translationXBy(-2000.0f).start();
        mTitle.animate().setDuration(1500).setStartDelay(500).translationXBy(-2000.0f).start();
    }
}