package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.ui.activities.HomeActivity;

public class DodajKosnicuFragment extends Fragment {

    private String imageUri;
    private Button cameraButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dodaj_kosnicu, container, false);

        ImageView imageView = view.findViewById(R.id.imageDodajKosnicu);
        FloatingActionButton removeImageButton = view.findViewById(R.id.buttonUkloniSliku);

        // Provera URI-ja
        Bundle args = getArguments();
        if (args != null) {
            imageUri = args.getString("image_uri");
            if (imageUri != null) {
                Glide.with(this).load(imageUri).into(imageView); // Učitavanje slike
                removeImageButton.setVisibility(View.VISIBLE);
            } else {
                Log.d("DodajKosnicuFragment", "imageUri je null");
            }
        } else {
            Log.d("DodajKosnicuFragment", "Bundle je null");
        }

        // Ostali kod za rukovanje dugmićima
        removeImageButton.setOnClickListener(v -> {
            imageView.setImageDrawable(null);
            imageUri = null;
            removeImageButton.setVisibility(View.GONE);
        });
        cameraButton = view.findViewById(R.id.buttonKamera);
        // Open Camera Fragment again
        cameraButton.setOnClickListener(v -> {
            CameraFragment cameraFragment = new CameraFragment();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, cameraFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        // Reset the System UI visibility
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }
}
