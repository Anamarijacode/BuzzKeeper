package com.opgkukic.buzzkeeper.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.Lokacija;
import com.opgkukic.buzzkeeper.model.Pčelinjak;
import com.opgkukic.buzzkeeper.model.SharedViewModel;

import java.util.Locale;

public class TipMjestaFragment extends Fragment {

    private static final String PREFS_NAME = "TipMjestaPrefs";
    private static final String SELECTED_PLACE_KEY = "SelectedPlace";
    private String language, odabranoMjesto;
    private Button spremiPcelinjak;
    private SharedViewModel sharedViewModel;
    private String naziv, datum,adresa,longituda,latituda,tipPcelinjaka,tipMjestaPcelinjaka;
    private FirebaseDatabase mDatabase;
    private Lokacija lokacija;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference = mDatabase.getReference("pcelinjaci");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_mjesta, container, false);
        spremiPcelinjak = view.findViewById(R.id.dodajPcelinjak);
        auth = FirebaseAuth.getInstance();
        language = requireContext().getResources().getConfiguration().getLocales().get(0).getLanguage();
        if(requireContext().getResources().getConfiguration().getLocales().get(0).getLanguage() == "")
        {
            Log.d("prijevodU tipumjesta", "onCreateView: en ");
        }

        setupImageView(view, R.id.imageLivada, "livada", "meadow");
        setupImageView(view, R.id.imageVoćnjak, "vocnjak", "orchard");
        setupImageView(view, R.id.imageVrt, "vrt", "garden");
        setupImageView(view, R.id.imageŠuma, "suma", "forest");
        setupImageView(view, R.id.imageVrhZgrade, "vrh_zgrade", "the_top_of_the_building");
        setupImageView(view, R.id.PrimorskoPodručje, "primorsko_podrucje", "coastal_area");
        setupImageView(view, R.id.imageBrda, "brda", "hills");
        setupImageView(view, R.id.imageOstalo, "ostalo", "the_rest");
        sharedViewModel.setTipMjestaPcelinjaka(odabranoMjesto);
        naziv = sharedViewModel.getNazivPčelinjaka().toString();
        datum=sharedViewModel.getDatumKreiranja().toString();
        adresa=sharedViewModel.getAdresa().toString();
        latituda=sharedViewModel.getLatituda().toString();
        longituda=sharedViewModel.getLongituda().toString();
        tipPcelinjaka=sharedViewModel.getTipPcelinjaka().toString();
        tipMjestaPcelinjaka=sharedViewModel.getTipMjestaPcelinjaka().toString();
        lokacija= new Lokacija(latituda,longituda);
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        spremiPcelinjak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pčelinjak pcelinjak = new Pčelinjak(datum, lokacija, naziv, tipMjestaPcelinjaka, tipPcelinjaka, userId);
                databaseReference.push().setValue(pcelinjak).addOnCompleteListener(dbTask ->
                {
                    if(dbTask.isSuccessful())
                    {
                        Fragment newFragment = new HomeFragment();
                        FragmentManager fragmentManager = getParentFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment_container, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Neuspješno spremanje", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    private void setupImageView(View rootView, int imageViewId, String resourceNameHr, String resourceNameEn) {
        ImageView imageView = rootView.findViewById(imageViewId);
        int drawableId = getDrawableIdForLanguage(resourceNameHr, resourceNameEn);
        imageView.setImageResource(drawableId);

        imageView.setOnClickListener(v -> {
            saveSelectedPlace(resourceNameHr);
            updateSelection(v);
        });
    }
private void updateSelection(View selectedView)
{
    resetImageSizeAndBorders();
    ImageView selectedImage = (ImageView) selectedView;
    selectedImage.setScaleX(0.98f);
    selectedImage.setScaleY(0.98f);
    selectedImage.setBackgroundResource(R.drawable.image_border);
}
private void resetImageSizeAndBorders()
{
    int [] imageViewsIds=
            {
                    R.id.imageLivada,
                    R.id.imageBrda,
                    R.id.imageOstalo,
                    R.id.imageŠuma,
                    R.id.imageVoćnjak,
                    R.id.imageVrhZgrade,
                    R.id.PrimorskoPodručje,
                    R.id.imageVrt,
            };
    for(int id: imageViewsIds)
    {
        ImageView imageView= requireView().findViewById(id);
        imageView.setScaleX(1f);
        imageView.setScaleY(1f);
        imageView.setBackgroundResource(0);
    }
}
    private int getDrawableIdForLanguage(String resourceNameHr, String resourceNameEn) {
        // Odabir imena resursa na temelju jezika
        String resourceName = language.equals("hr") ? resourceNameHr : resourceNameEn;
        return getResources().getIdentifier(resourceName, "drawable", requireContext().getPackageName());
    }

    private void saveSelectedPlace(String selectedPlace) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SELECTED_PLACE_KEY, selectedPlace);
        editor.apply();
        odabranoMjesto = selectedPlace;

    }

    @Nullable
    public String getSelectedPlace() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SELECTED_PLACE_KEY, null);
    }
}
//package com.opgkukic.buzzkeeper.ui.fragments;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//import androidx.fragment.app.Fragment;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.opgkukic.buzzkeeper.R;
//
//import java.util.HashMap;
//
//public class TipMjestaFragment extends Fragment {
//
//    private ImageView selectedImageView = null;
//    private String selectedPlace = null;
//    private Button saveButton;
//    private DatabaseReference databaseReference;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_tip_mjesta, container, false);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("pcelinjak3");
//
//        saveButton = view.findViewById(R.id.submitButton);
//        setupImageView(view, R.id.imageLivada, "livada");
//        setupImageView(view, R.id.imageVoćnjak, "voćnjak");
//        setupImageView(view, R.id.imageVrt, "vrt");
//        setupImageView(view, R.id.imageŠuma, "šuma");
//        setupImageView(view, R.id.imageVrhZgrade, "vrh_zgrade");
//        setupImageView(view, R.id.PrimorskoPodručje, "primorsko_područje");
//        setupImageView(view, R.id.imageBrda, "brda");
//        setupImageView(view, R.id.imageOstalo, "ostalo");
//
//        saveButton.setOnClickListener(v -> saveToFirebase());
//
//        return view;
//    }
//
//    private void setupImageView(View rootView, int imageViewId, String placeName) {
//        ImageView imageView = rootView.findViewById(imageViewId);
//        imageView.setOnClickListener(v -> {
//            if (selectedImageView != null) {
//                selectedImageView.setBackgroundResource(0); // Ukloni border s prethodne slike
//            }
//            selectedImageView = imageView;
//            selectedImageView.setBackgroundResource(R.drawable.selected_border); // Postavi border na odabranu sliku
//            selectedPlace = placeName;
//            Toast.makeText(requireContext(), "Odabrano mjesto: " + placeName, Toast.LENGTH_SHORT).show();
//        });
//    }
//
//    private void saveToFirebase() {
//        if (selectedPlace == null) {
//            Toast.makeText(requireContext(), "Molimo odaberite mjesto prije spremanja.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        HashMap<String, Object> pcelinjakData = new HashMap<>();
//        pcelinjakData.put("nazivPčelinjaka", "pcelinjak 3");
//        pcelinjakData.put("datumKreiranja", "2025-08-01"); // Ovdje dodajte odgovarajuće podatke
//        pcelinjakData.put("tipPčelinjaka", "stacionar"); // Prema vašoj logici
//        pcelinjakData.put("lokacija", new HashMap<String, Double>() {{
//            put("latitude", 45.845399);
//            put("longitude", 15.966568);
//        }});
//        pcelinjakData.put("userId", "userId1");
//        pcelinjakData.put("mjesto", selectedPlace); // Dodajte odabrano mjesto
//
//        databaseReference.push().setValue(pcelinjakData).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                Toast.makeText(requireContext(), "Podaci uspješno spremljeni u Firebase!", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(requireContext(), "Greška pri spremanju podataka.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
