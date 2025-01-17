package com.opgkukic.buzzkeeper.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.SharedViewModel;

import org.checkerframework.checker.nullness.qual.NonNull;

public class TipPcelinjakaFragment extends Fragment {
    private ImageView stacionarp, pokretnip;
    private DatabaseReference databaseReference;
    private SharedViewModel sharedViewModel;
    private String odabrani;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_pcelinjaka, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        stacionarp = view.findViewById(R.id.stacionarp);
        pokretnip = view.findViewById(R.id.pokretnip);
        databaseReference = FirebaseDatabase.getInstance().getReference("pcelinjaci");

        stacionarp.setOnClickListener(v ->
                updateSelection(v));
        pokretnip.setOnClickListener(v ->
                updateSelection(v));

        sharedViewModel.setTipPcelinjaka(odabrani);
        return view;
    }

//    private void saveTipPcelinjaka(String tip) {
//        String pcelinjakId = "pcelinjak3"; // ID odabranog pčelinjaka
//        databaseReference.child(pcelinjakId).child("tipPcelinjaka").setValue(tip)
//                .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Tip pčelinjaka spremljen!", Toast.LENGTH_SHORT).show())
//                .addOnFailureListener(e -> Toast.makeText(getContext(), "Greška pri spremanju.", Toast.LENGTH_SHORT).show());
//    }

    private void updateSelection(View selectedView)
    {
        resetImageSizeAndBorders();
        ImageView selectedImage = (ImageView) selectedView;
        selectedImage.setScaleX(0.98f);
        selectedImage.setScaleY(0.98f);
        selectedImage.setBackgroundResource(R.drawable.image_border);
        if(selectedView.equals(R.id.stacionarp))
        {
            odabrani ="stacionar";
        }
        else if(selectedView.equals(R.id.pokretnip))
        {
            odabrani="pokreni";
        }
    }
    private void resetImageSizeAndBorders()
    {
        int [] imageViewsIds=
                {
                        R.id.pokretnip,
                        R.id.stacionarp,
                };
        for(int id: imageViewsIds)
        {
            ImageView imageView= requireView().findViewById(id);
            imageView.setScaleX(1f);
            imageView.setScaleY(1f);
            imageView.setBackgroundResource(0);
        }
    }

}
