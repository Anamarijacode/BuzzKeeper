package com.opgkukic.buzzkeeper.ui.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.PcelinjakAdapter;
import com.opgkukic.buzzkeeper.model.Pčelinjak;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

private static final String TAG = "HomeFragment";
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private RecyclerView recyclerView;
    private PcelinjakAdapter adapter;
    private List<Pčelinjak> pcelinjaciList;
    private FloatingActionButton dodajPcelinjak;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pcelinjaciList = new ArrayList<>();
        adapter = new PcelinjakAdapter(pcelinjaciList);
        recyclerView.setAdapter(adapter);
        dodajPcelinjak =view.findViewById(R.id.fab_add);
        auth = FirebaseAuth.getInstance();

        dodajPcelinjak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new DodajPcelinjakFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ReadData();
        enableMyLocation();
        return view;
    }
    private void enableMyLocation() {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);
        };

    }

    private void ReadData() {
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        DatabaseReference pcelinjaci = mDatabase.child("pcelinjaci");
        pcelinjaci.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pcelinjaciList.clear();
                for (DataSnapshot pcelinjaciSnapshot : snapshot.getChildren()) {
                    Pčelinjak pcelinjak = pcelinjaciSnapshot.getValue(Pčelinjak.class);
                    Log.d("pcelinjakUser", "user" + pcelinjak.getUserId());
                    Log.d("pcelinjakUser", "user" + userId);

                            if (pcelinjak != null) {
                                if(userId.equals(pcelinjak.getUserId())) {
                                    pcelinjaciList.add(pcelinjak);
                                }
                            }


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }
}