package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.annotations.Nullable;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.SinglePcelinjakAdapter;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import org.checkerframework.checker.nullness.qual.NonNull;

public class PcelinjakSingleFragment extends Fragment {
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pcelinjak_single, container, false);

        // Povezivanje sa elementima iz XML-a
//        drawerLayout = view.findViewById(R.id.drawerLayout);
//        navView = view.findViewById(R.id.navView);
        //Toolbar toolbar = view.findViewById(R.id.toolbar);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPagerSinglePagePcelinjak);
        WormDotsIndicator wormDotsIndicator = view.findViewById(R.id.dots_indicatorSinglePagePcelinjak);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() == null) {
            //activity.setSupportActionBar(toolbar);

//            if (drawerLayout != null) {
//                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                        //activity, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
//                //drawerLayout.addDrawerListener(toggle);
//                //toggle.syncState();
//            }
        }

        // Podešavanje NavigationView listenera
        if (navView != null) {
            navView.setNavigationItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.detalji:
                        // Logika za prvu opciju
                        break;
                    case R.id.uredi:
                        // Logika za drugu opciju
                        break;
                }
                if (drawerLayout != null) {
                    drawerLayout.closeDrawer(navView);
                }
                return true;
            });
        }

        // Postavljanje ViewPager adaptera
        Bundle args = getArguments();
        SinglePcelinjakAdapter adapter = new SinglePcelinjakAdapter(this, args != null ? args : new Bundle());
        viewPager2.setAdapter(adapter);
        wormDotsIndicator.setViewPager2(viewPager2);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
