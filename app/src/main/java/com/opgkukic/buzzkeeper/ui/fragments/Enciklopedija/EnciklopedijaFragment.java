package com.opgkukic.buzzkeeper.ui.fragments.Enciklopedija;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.EnciklopedijaAdapter;
import com.opgkukic.buzzkeeper.model.Enciklopedija;

import java.util.List;


public class EnciklopedijaFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private RecyclerView recyclerView;
    private EnciklopedijaAdapter adapter;
    private List<Enciklopedija> enciklopedijaList;

    public static EnciklopedijaFragment newInstance(String param1, String param2) {
        EnciklopedijaFragment fragment = new EnciklopedijaFragment();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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