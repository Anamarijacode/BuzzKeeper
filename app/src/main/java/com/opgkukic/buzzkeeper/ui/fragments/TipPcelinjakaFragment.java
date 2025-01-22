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
import com.google.firebase.database.annotations.Nullable;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.SharedViewModel;

import org.checkerframework.checker.nullness.qual.NonNull;

public class TipPcelinjakaFragment extends Fragment {
    private ImageView stacionarp, pokretnip;
    private SharedViewModel sharedViewModel;
    private String odabrani;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_pcelinjaka, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        stacionarp = view.findViewById(R.id.stacionarp);
        pokretnip = view.findViewById(R.id.pokretnip);

        stacionarp.setOnClickListener(v ->
                updateSelection(v));
        pokretnip.setOnClickListener(v ->
                updateSelection(v));

        return view;
    }



    private void updateSelection(View selectedView) {
        resetImageSizeAndBorders();
        ImageView selectedImage = (ImageView) selectedView;
        selectedImage.setScaleX(0.98f);
        selectedImage.setScaleY(0.98f);
        selectedImage.setBackgroundResource(R.drawable.image_border);

        int viewId = selectedView.getId();
        if (viewId == R.id.stacionarp) {
            odabrani = "stacionar";
        } else if (viewId == R.id.pokretnip) {
            odabrani = "pokretni";
        }

        sharedViewModel.setTipPcelinjaka(odabrani);
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
