package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import com.opgkukic.buzzkeeper.model.Lokacija;
import com.opgkukic.buzzkeeper.model.PcelinjakDataHolder;
import com.opgkukic.buzzkeeper.model.Pčelinjak;


public class DodajPcelinjakFragment extends Fragment {

    public ViewPager2 viewPager;
    //private PcelinjakPagerAdapter pagerAdapter;
    private Button nextButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dodaj_pcelinjak, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        nextButton = view.findViewById(R.id.nextButton);

//        pagerAdapter = new PcelinjakPagerAdapter(this);
//        viewPager.setAdapter(pagerAdapter);
//
//        nextButton.setOnClickListener(v -> {
//            if (viewPager.getCurrentItem() < pagerAdapter.getItemCount() - 1) {
//                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//            } else {
//                savePcelinjak();
//            }
//        });

        return view;
    }

    private void savePcelinjak() {
        // Firebase spremanje na kraju svih koraka
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("pcelinjaci");
        String id = mDatabase.push().getKey();

        Pčelinjak pcelinjak = new Pčelinjak(
                PcelinjakDataHolder.nazivPcelinjaka,
                new Lokacija(PcelinjakDataHolder.latitude, PcelinjakDataHolder.longitude),
                PcelinjakDataHolder.tipMjesta,
                PcelinjakDataHolder.tipPcelinjaka,
                "userId1"
        );
        if (id != null) {
            mDatabase.child(id).setValue(pcelinjak);
            Toast.makeText(getContext(), "Pčelinjak uspješno dodat!", Toast.LENGTH_SHORT).show();
        }
    }
}