package com.opgkukic.buzzkeeper.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.firebase.database.annotations.NotNull;
import com.opgkukic.buzzkeeper.ui.fragments.PcelinjakSingleDateAndTimeFragment;
import com.opgkukic.buzzkeeper.ui.fragments.PcelinjakSingleKosniceFragment;

public class SinglePcelinjakAdapter extends FragmentStateAdapter {
    private final Bundle data;

    public SinglePcelinjakAdapter(@NotNull Fragment fragment, Bundle data) {
        super(fragment);
        this.data = data;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new PcelinjakSingleKosniceFragment();
                break;
            case 1:
                fragment = new PcelinjakSingleDateAndTimeFragment();
                break;
            default:
                fragment = new PcelinjakSingleKosniceFragment();
                break;
        }
        fragment.setArguments(data); // Postavljanje argumenata
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
