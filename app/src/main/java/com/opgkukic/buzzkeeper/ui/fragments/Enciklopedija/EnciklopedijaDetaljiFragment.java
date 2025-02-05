package com.opgkukic.buzzkeeper.ui.fragments.Enciklopedija;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opgkukic.buzzkeeper.R;

public class EnciklopedijaDetaljiFragment extends Fragment {


    public static EnciklopedijaDetaljiFragment newInstance(String param1, String param2) {
        EnciklopedijaDetaljiFragment fragment = new EnciklopedijaDetaljiFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enciklopedija_detalji, container, false);
        return view;
    }
}