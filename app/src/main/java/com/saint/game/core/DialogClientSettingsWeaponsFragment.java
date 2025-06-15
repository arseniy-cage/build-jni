package com.saint.game.core;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.fragment.app.Fragment;
import com.nvidia.devtech.NvEventQueueActivity;
import java.util.HashMap;
import com.saint.game.R;

public class DialogClientSettingsWeaponsFragment extends Fragment implements ISaveableFragment {
    public boolean bChangeAllowed = true;
    public NvEventQueueActivity mContext = null;
    private SeekBar.OnSeekBarChangeListener mListenerSeekBars;
    private HashMap<ViewGroup, Drawable> mOldDrawables;
    public ViewGroup mParentView = null;
    private View mRootView = null;

    public void save() {
    }

    public static DialogClientSettingsWeaponsFragment createInstance(String str) {
        return new DialogClientSettingsWeaponsFragment();
    }

    public DialogClientSettingsWeaponsFragment setRoot(ViewGroup viewGroup) {
        this.mParentView = viewGroup;
        return this;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.dialog_settings_weapons, viewGroup, false);
        this.mContext = (NvEventQueueActivity) getActivity();
        getValues();
        setSeekBarListeners();
        return this.mRootView;
    }

    public void makeAllElementsInvisible(ViewGroup viewGroup, View view, boolean z) {
        if (z) {
            HashMap<ViewGroup, Drawable> hashMap = new HashMap<>();
            this.mOldDrawables = hashMap;
            hashMap.put(viewGroup, viewGroup.getBackground());
            viewGroup.setBackground(new ColorDrawable(0));
        }
        if (viewGroup != null) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    ViewGroup viewGroup2 = (ViewGroup) childAt;
                    makeAllElementsInvisible(viewGroup2, view, false);
                    this.mOldDrawables.put(viewGroup2, viewGroup2.getBackground());
                    childAt.setBackground(new ColorDrawable(0));
                } else if (childAt != view) {
                    childAt.setAlpha(0.0f);
                }
            }
        }
    }

    public void makeAllElementsVisible(ViewGroup viewGroup, View view, boolean z) {
        if (z) {
            for (ViewGroup next : this.mOldDrawables.keySet()) {
                next.setBackground(this.mOldDrawables.get(next));
            }
        }
        if (viewGroup != null) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    makeAllElementsVisible((ViewGroup) childAt, view, false);
                } else if (childAt != view) {
                    childAt.setAlpha(1.0f);
                }
            }
        }
    }

    private void setSeekBarListeners() {
        this.mListenerSeekBars = new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (DialogClientSettingsWeaponsFragment.this.bChangeAllowed) {
                    DialogClientSettingsWeaponsFragment.this.passValuesToNative();
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                DialogClientSettingsWeaponsFragment dialogClientSettingsWeaponsFragment = DialogClientSettingsWeaponsFragment.this;
                dialogClientSettingsWeaponsFragment.makeAllElementsInvisible(dialogClientSettingsWeaponsFragment.mParentView, seekBar, true);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                DialogClientSettingsWeaponsFragment dialogClientSettingsWeaponsFragment = DialogClientSettingsWeaponsFragment.this;
                dialogClientSettingsWeaponsFragment.makeAllElementsVisible(dialogClientSettingsWeaponsFragment.mParentView, seekBar, true);
                DialogClientSettingsWeaponsFragment.this.mContext.onSettingsWindowSave();
            }
        };
        for (int i = 12; i < 14; i++) {
            int identifier = this.mContext.getResources().getIdentifier("hud_element_pos_x_" + i, "id", this.mContext.getPackageName());
            int identifier2 = this.mContext.getResources().getIdentifier("hud_element_pos_y_" + i, "id", this.mContext.getPackageName());
            SeekBar seekBar = (SeekBar) this.mRootView.findViewById(identifier);
            SeekBar seekBar2 = (SeekBar) this.mRootView.findViewById(identifier2);
            if (seekBar != null) {
                seekBar.setOnSeekBarChangeListener(this.mListenerSeekBars);
            }
            if (seekBar2 != null) {
                seekBar2.setOnSeekBarChangeListener(this.mListenerSeekBars);
            }
        }
        for (int i2 = 12; i2 < 14; i2++) {
            int identifier3 = this.mContext.getResources().getIdentifier("hud_element_scale_x_" + i2, "id", this.mContext.getPackageName());
            int identifier4 = this.mContext.getResources().getIdentifier("hud_element_scale_y_" + i2, "id", this.mContext.getPackageName());
            SeekBar seekBar3 = (SeekBar) this.mRootView.findViewById(identifier3);
            SeekBar seekBar4 = (SeekBar) this.mRootView.findViewById(identifier4);
            if (seekBar3 != null) {
                seekBar3.setOnSeekBarChangeListener(this.mListenerSeekBars);
            }
            if (seekBar4 != null) {
                seekBar4.setOnSeekBarChangeListener(this.mListenerSeekBars);
            }
        }
    }

    public void getValues() {
        this.bChangeAllowed = false;
        for (int i = 12; i < 14; i++) {
            int identifier = this.mContext.getResources().getIdentifier("hud_element_pos_x_" + i, "id", this.mContext.getPackageName());
            int identifier2 = this.mContext.getResources().getIdentifier("hud_element_pos_y_" + i, "id", this.mContext.getPackageName());
            SeekBar seekBar = (SeekBar) this.mRootView.findViewById(identifier);
            SeekBar seekBar2 = (SeekBar) this.mRootView.findViewById(identifier2);
            int[] nativeHudElementPosition = this.mContext.getNativeHudElementPosition(i);
            if (nativeHudElementPosition[0] == -1) {
                nativeHudElementPosition[0] = 1;
            }
            if (nativeHudElementPosition[1] == -1) {
                nativeHudElementPosition[1] = 1;
            }
            if (seekBar != null) {
                seekBar.setProgress(nativeHudElementPosition[0]);
            }
            if (seekBar2 != null) {
                seekBar2.setProgress(nativeHudElementPosition[1]);
            }
        }
        for (int i2 = 12; i2 < 14; i2++) {
            int identifier3 = this.mContext.getResources().getIdentifier("hud_element_scale_x_" + i2, "id", this.mContext.getPackageName());
            int identifier4 = this.mContext.getResources().getIdentifier("hud_element_scale_y_" + i2, "id", this.mContext.getPackageName());
            SeekBar seekBar3 = (SeekBar) this.mRootView.findViewById(identifier3);
            SeekBar seekBar4 = (SeekBar) this.mRootView.findViewById(identifier4);
            int[] nativeHudElementScale = this.mContext.getNativeHudElementScale(i2);
            if (nativeHudElementScale[0] == -1) {
                nativeHudElementScale[0] = 1;
            }
            if (nativeHudElementScale[1] == -1) {
                nativeHudElementScale[1] = 1;
            }
            if (!(seekBar3 == null || nativeHudElementScale[0] == -1)) {
                seekBar3.setProgress(nativeHudElementScale[0]);
            }
            if (!(seekBar4 == null || nativeHudElementScale[1] == -1)) {
                seekBar4.setProgress(nativeHudElementScale[1]);
            }
        }
        this.bChangeAllowed = true;
    }

    public void passValuesToNative() {
        int i = 12;
        while (true) {
            int i2 = -1;
            if (i >= 14) {
                break;
            }
            int identifier = this.mContext.getResources().getIdentifier("hud_element_pos_x_" + i, "id", this.mContext.getPackageName());
            int identifier2 = this.mContext.getResources().getIdentifier("hud_element_pos_y_" + i, "id", this.mContext.getPackageName());
            SeekBar seekBar = (SeekBar) this.mRootView.findViewById(identifier);
            SeekBar seekBar2 = (SeekBar) this.mRootView.findViewById(identifier2);
            int progress = seekBar != null ? seekBar.getProgress() : -1;
            if (seekBar2 != null) {
                i2 = seekBar2.getProgress();
            }
            this.mContext.setNativeHudElementPosition(i, progress, i2);
            i++;
        }
        for (int i3 = 12; i3 < 14; i3++) {
            int identifier3 = this.mContext.getResources().getIdentifier("hud_element_scale_x_" + i3, "id", this.mContext.getPackageName());
            int identifier4 = this.mContext.getResources().getIdentifier("hud_element_scale_y_" + i3, "id", this.mContext.getPackageName());
            SeekBar seekBar3 = (SeekBar) this.mRootView.findViewById(identifier3);
            SeekBar seekBar4 = (SeekBar) this.mRootView.findViewById(identifier4);
            this.mContext.setNativeHudElementScale(i3, seekBar3 != null ? seekBar3.getProgress() : -1, seekBar4 != null ? seekBar4.getProgress() : -1);
        }
    }
}
