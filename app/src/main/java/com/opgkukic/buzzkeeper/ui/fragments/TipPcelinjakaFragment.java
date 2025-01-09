//package com.opgkukic.buzzkeeper.ui.fragments;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Spinner;
//
//import com.google.firebase.database.annotations.Nullable;
//import com.opgkukic.buzzkeeper.R;
//import com.opgkukic.buzzkeeper.model.PcelinjakDataHolder;
//
//
//public class TipPcelinjakaFragment extends Fragment {
//    private Spinner spinnerTipPcelinjaka;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_tip_pcelinjaka, container, false);
//        spinnerTipPcelinjaka = view.findViewById(R.id.spinnerTipPcelinjaka);
//        view.findViewById(R.id.nextButton).setOnClickListener(v -> {
//            PcelinjakDataHolder.tipPcelinjaka = spinnerTipPcelinjaka.getSelectedItem().toString();
//            ((DodajPcelinjakFragment) getParentFragment()).viewPager.setCurrentItem(3);
//        });
//        return view;
//    }
//
//}