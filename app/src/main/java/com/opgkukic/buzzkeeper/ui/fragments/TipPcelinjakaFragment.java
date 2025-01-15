package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.opgkukic.buzzkeeper.R;

import org.checkerframework.checker.nullness.qual.NonNull;

public class TipPcelinjakaFragment extends Fragment {
    private ImageView stacionarp, pokretnip;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_pcelinjaka, container, false);

        stacionarp = view.findViewById(R.id.stacionarp);
        pokretnip = view.findViewById(R.id.pokretnip);
        databaseReference = FirebaseDatabase.getInstance().getReference("pcelinjaci");

        stacionarp.setOnClickListener(v -> saveTipPcelinjaka("stacionar"));
        pokretnip.setOnClickListener(v -> saveTipPcelinjaka("pokretni"));

        return view;
    }

    private void saveTipPcelinjaka(String tip) {
        String pcelinjakId = "pcelinjak3"; // ID odabranog pčelinjaka
        databaseReference.child(pcelinjakId).child("tipPcelinjaka").setValue(tip)
                .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Tip pčelinjaka spremljen!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Greška pri spremanju.", Toast.LENGTH_SHORT).show());
    }
}
