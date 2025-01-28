package com.opgkukic.buzzkeeper.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.opgkukic.buzzkeeper.ui.fragments.DodajKosnicuFragment;
import com.opgkukic.buzzkeeper.ui.fragments.DodajKosnicuFragment2;
import com.opgkukic.buzzkeeper.ui.fragments.DodajKosnicuFragment3;

import org.jetbrains.annotations.NotNull;

public class DodajKosnicuAdapter extends FragmentStateAdapter {
    private final Bundle data;
    public DodajKosnicuAdapter(@NotNull Fragment fragmentActivity, Bundle data) {
        super(fragmentActivity);
        this.data = data;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new DodajKosnicuFragment(); // Fragment za dodavanje slike
                break;
            case 1:
                fragment = new DodajKosnicuFragment2(); // Sledeći korak
                break;
            case 2:
                fragment = new DodajKosnicuFragment3(); // Završni korak
                break;
            default:
                fragment = new DodajKosnicuFragment();
                break;
        }

        // Prosleđivanje podataka fragmentu
        fragment.setArguments(data);
        return fragment;
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}
