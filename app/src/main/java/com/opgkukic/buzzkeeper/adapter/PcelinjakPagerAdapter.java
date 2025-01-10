package com.opgkukic.buzzkeeper.adapter;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.opgkukic.buzzkeeper.ui.fragments.LokacijaFragment;
import com.opgkukic.buzzkeeper.ui.fragments.NazivFragment;
import com.opgkukic.buzzkeeper.ui.fragments.TipMjestaFragment;
import com.opgkukic.buzzkeeper.ui.fragments.TipPcelinjakaFragment;

import org.checkerframework.checker.nullness.qual.NonNull;

public class PcelinjakPagerAdapter extends FragmentStateAdapter {
    public PcelinjakPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new NazivFragment();
            case 1: return new LokacijaFragment();
            case 2: return new TipPcelinjakaFragment();
            case 3: return new TipMjestaFragment();
            default: return new NazivFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Četiri koraka dodavanja
    }
}
