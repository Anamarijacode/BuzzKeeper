package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private RecyclerView recyclerView;
    private PcelinjakAdapter adapter;
    private List<Pčelinjak> pcelinjaciList;

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

        ReadData();
        return view;
    }

    private void ReadData() {
        DatabaseReference pcelinjaci = mDatabase.child("pcelinjaci");
        pcelinjaci.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pcelinjaciList.clear();
                for (DataSnapshot pcelinjaciSnapshot : snapshot.getChildren()) {
                    Pčelinjak pcelinjak = pcelinjaciSnapshot.getValue(Pčelinjak.class);
                    if (pcelinjak != null) {
                        pcelinjaciList.add(pcelinjak);
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