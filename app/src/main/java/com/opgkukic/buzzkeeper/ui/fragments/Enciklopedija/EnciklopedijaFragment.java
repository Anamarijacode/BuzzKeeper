package com.opgkukic.buzzkeeper.ui.fragments.Enciklopedija;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.opgkukic.buzzkeeper.R;


public class EnciklopedijaFragment extends Fragment {

    public static EnciklopedijaFragment newInstance(String param1, String param2) {
        EnciklopedijaFragment fragment = new EnciklopedijaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enciklopedija, container, false);
        return view;
    }
}