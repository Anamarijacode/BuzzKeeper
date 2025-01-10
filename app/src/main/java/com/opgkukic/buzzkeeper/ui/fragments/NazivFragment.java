package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.annotations.Nullable;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.PcelinjakDataHolder;


public class NazivFragment extends Fragment {

    private EditText etNaziv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_naziv, container, false);
//        etNaziv = view.findViewById(R.id.etNaziv);
//        view.findViewById(R.id.nextButton).setOnClickListener(v -> {
//            PcelinjakDataHolder.nazivPcelinjaka = etNaziv.getText().toString();
//            ((DodajPcelinjakFragment) getParentFragment()).viewPager.setCurrentItem(1);
//        });
        return view;
    }
}