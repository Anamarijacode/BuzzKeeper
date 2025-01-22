package com.opgkukic.buzzkeeper.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.SinglePcelinjakAdapter;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class PcelinjakSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcelinjak_single);

        // Retrieve data from intent
        Intent intent = getIntent();
        Bundle args = new Bundle();
        args.putString("nazivPcelinjaka", intent.getStringExtra("nazivPcelinjaka"));
        args.putString("tipPcelinjaka", intent.getStringExtra("tipPcelinjaka"));
        args.putString("tipMjesta", intent.getStringExtra("tipMjesta"));
        args.putString("latituda", intent.getStringExtra("latituda"));
        args.putString("longituda", intent.getStringExtra("longituda"));
        args.putString("userId", intent.getStringExtra("userId"));

        // Use the bundle to set up the adapter
        ViewPager2 viewPager2 = findViewById(R.id.viewPagerSinglePagePcelinjak);
        WormDotsIndicator wormDotsIndicator = findViewById(R.id.dots_indicatorSinglePagePcelinjak);
        SinglePcelinjakAdapter adapter = new SinglePcelinjakAdapter(new Fragment(), args);
        viewPager2.setAdapter(adapter);
        wormDotsIndicator.setViewPager2(viewPager2);
    }

}

