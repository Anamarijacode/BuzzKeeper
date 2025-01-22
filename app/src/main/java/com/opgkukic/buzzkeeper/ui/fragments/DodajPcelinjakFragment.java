package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.opgkukic.buzzkeeper.R;
//import com.opgkukic.buzzkeeper.adapter.PcelinjakPagerAdapter;
import com.opgkukic.buzzkeeper.adapter.PcelinjakPagerAdapter;
import com.opgkukic.buzzkeeper.model.Lokacija;
import com.opgkukic.buzzkeeper.model.PcelinjakDataHolder;
import com.opgkukic.buzzkeeper.model.Pčelinjak;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;


public class DodajPcelinjakFragment extends Fragment {

    public ViewPager2 viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dodaj_pcelinjak, container, false);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        WormDotsIndicator wormDotsIndicator = view.findViewById(R.id.dots_indicator);
        PcelinjakPagerAdapter adapter = new PcelinjakPagerAdapter(this);
        viewPager2.setAdapter(adapter);
        wormDotsIndicator.setViewPager2(viewPager2);


        return view;
    }


}