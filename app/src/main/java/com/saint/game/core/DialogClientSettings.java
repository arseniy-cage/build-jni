package com.saint.game.core;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.nvidia.devtech.NvEventQueueActivity;

import com.saint.game.R;

public class DialogClientSettings extends DialogFragment {
    static final int mSettingsComonEnd = 15;
    static final int mSettingsComonStart = 10;
    static final int mSettingsHudCount = 10;
    static final int mSettingsHudFPSEnd = 12;
    static final int mSettingsHudFPSStart = 10;
    static final int mSettingsWeaponsEnd = 14;
    static final int mSettingsWeaponsStart = 12;
    NvEventQueueActivity mContext = null;
    TabLayout tabLayout;
    ViewPager viewPager;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.dialog_settings, (ViewGroup) null, false);
        this.tabLayout = (TabLayout) inflate.findViewById(R.id.tabLayout);
        this.viewPager = (ViewPager) inflate.findViewById(R.id.masterViewPager);
        final DialogClientSettingsAdapter dialogClientSettingsAdapter = new DialogClientSettingsAdapter(getChildFragmentManager(), 0);
        dialogClientSettingsAdapter.addFragment("Основное", DialogClientSettingsCommonFragment.createInstance("common"));
        dialogClientSettingsAdapter.addFragment("HUD", DialogClientSettingsHUDFragment.createInstance("hud").setRoot((ViewGroup) inflate.findViewById(R.id.ll_settings_root)));
        this.viewPager.setAdapter(dialogClientSettingsAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getDialog().getWindow().setDimAmount(0.0f);
        this.mContext = (NvEventQueueActivity) getActivity();
        ((AppCompatButton) inflate.findViewById(R.id.dialog_settings_button_close)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogClientSettings.this.mContext.onSettingsWindowSave();
                DialogClientSettings.this.getDialog().dismiss();
            }
        });
        ((AppCompatButton) inflate.findViewById(R.id.dialog_settings_button_reset)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogClientSettings.this.mContext.onSettingsWindowDefaults(DialogClientSettings.this.tabLayout.getSelectedTabPosition() + 1);
                ((ISaveableFragment) dialogClientSettingsAdapter.getItem(DialogClientSettings.this.tabLayout.getSelectedTabPosition())).getValues();
                DialogClientSettings.this.mContext.onSettingsWindowSave();
            }
        });
        setCancelable(false);
        return inflate;
    }
}
