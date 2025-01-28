package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.DodajKosnicuAdapter;
import com.opgkukic.buzzkeeper.ui.activities.HomeActivity;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;


public class DodajKosnicuView2Fragment extends Fragment {

    public ViewPager2 viewPager2;
    public WormDotsIndicator wormDotsIndicator;
    Bundle args;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dodaj_kosnicu_view2, container, false);
        viewPager2=view.findViewById(R.id.viewPager2DodajKosnicu);
        args = getArguments(); // Dobijanje prosleđenih podataka

        if (args != null) {
            String imageUri = args.getString("image_uri");
            if (imageUri != null) {
                // Log za proveru URI-ja
                Log.d("DodajKosnicuView2Fragment", "Primljen URI: " + imageUri);
            }
        }
        wormDotsIndicator=view.findViewById(R.id.dots_indicatorDodajKosnicu);
        DodajKosnicuAdapter adapter= new DodajKosnicuAdapter(this, args != null ? args : new Bundle());
        viewPager2.setAdapter(adapter);
        wormDotsIndicator.setViewPager2(viewPager2);

        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity) getActivity()).showBottomNavigationView();
        }



        return view;
    }
    @Override
    public void onPause() {
        super.onPause();

        // Resetovanje System UI Visibility-a
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity) getActivity()).showBottomNavigationView();
        }
    }

}