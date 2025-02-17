package com.opgkukic.buzzkeeper.ui.fragments.Kosnice.DodajKosnicu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.SharedViewModel;
import com.opgkukic.buzzkeeper.ui.fragments.CameraFragment;

import org.checkerframework.checker.nullness.qual.NonNull;

public class DodajKosnicuFragment extends Fragment {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 200;
    private String imageUri;
    private ImageButton cameraButton, galeriButton;
    private ImageView imageView;
    private FloatingActionButton removeImageButton;
    private SharedViewModel viewModel;

    private static final int GALLERY_REQUEST_CODE = 1; // request code for gallery

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dodaj_kosnicu, container, false);

        imageView = view.findViewById(R.id.imageDodajKosnicu);
        removeImageButton = view.findViewById(R.id.buttonUkloniSliku);
        cameraButton = view.findViewById(R.id.buttonKamera);
        galeriButton = view.findViewById(R.id.buttonGalerija);

        // Provera URI-ja (ako postoji)
        Bundle args = getArguments();
        if (args != null) {
            imageUri = args.getString("image_uri");
            if (imageUri != null) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(this).load(imageUri).into(imageView);
                removeImageButton.setVisibility(View.VISIBLE);
            } else {
                Log.d("DodajKosnicuFragment", "imageUri je null");
            }
        } else {
            Log.d("DodajKosnicuFragment", "Bundle je null");
        }

        // Uklanjanje slike
        removeImageButton.setOnClickListener(v -> {
            imageView.setImageDrawable(null);
            imageUri = null;
            removeImageButton.setVisibility(View.GONE);
        });

        // Dugme za kameru
        cameraButton.setOnClickListener(v -> {
            if (hasCameraPermission()) {
                openCameraFragment();
            } else {
                requestCameraPermission();
            }
        });

        // Dugme za otvaranje galerije
        galeriButton.setOnClickListener(v -> openGallery());

        return view;
    }

    // Metoda za otvaranje galerije
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE); // Otvorite galeriju
    }

    // Provjera ima li aplikacija dozvolu za kameru
    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    // Traženje dozvole za kameru
    private void requestCameraPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
    }

    // Metoda za otvaranje CameraFragmenta
    private void openCameraFragment() {
        CameraFragment cameraFragment = new CameraFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, cameraFragment)
                .addToBackStack(null)
                .commit();
    }

    // Obrada korisnikovog odgovora na traženje dozvole
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCameraFragment();
            } else {
                Log.e("DodajKosnicuFragment", "Dozvola za kameru nije odobrena");
            }
        }
    }

    // Obrada rezultata iz galerije (sa starim onActivityResult)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // Ispisivanje URI-ja za debagovanje
                Log.d("DodajKosnicuFragment", "URI slike: " + selectedImageUri.toString());
                imageUri = selectedImageUri.toString();
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(requireContext()).load(selectedImageUri).into(imageView);
                removeImageButton.setVisibility(View.VISIBLE);
            } else {
                Log.e("DodajKosnicuFragment", "URI slike je null");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Reset UI
        requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }
}
