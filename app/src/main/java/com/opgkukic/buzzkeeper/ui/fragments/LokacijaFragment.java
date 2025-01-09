//package com.opgkukic.buzzkeeper.ui.fragments;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//
//import com.google.firebase.database.annotations.Nullable;
//import com.opgkukic.buzzkeeper.R;
//import com.opgkukic.buzzkeeper.model.PcelinjakDataHolder;
//
//
//public class LokacijaFragment extends Fragment {
//
//    private EditText etLatitude, etLongitude;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_lokacija, container, false);
//        etLatitude = view.findViewById(R.id.etLatitude);
//        etLongitude = view.findViewById(R.id.etLongitude);
//        view.findViewById(R.id.nextButton).setOnClickListener(v -> {
//            PcelinjakDataHolder.latitude = Double.parseDouble(etLatitude.getText().toString());
//            PcelinjakDataHolder.longitude = Double.parseDouble(etLongitude.getText().toString());
//            ((DodajPcelinjakFragment) getParentFragment()).viewPager.setCurrentItem(2);
//        });
//        return view;
//    }
//}