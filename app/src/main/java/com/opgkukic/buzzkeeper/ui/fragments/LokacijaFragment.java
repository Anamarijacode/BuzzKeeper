package com.opgkukic.buzzkeeper.ui.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.firebase.database.annotations.Nullable;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.PlaceAutocompleteAdapter;
import com.opgkukic.buzzkeeper.model.PcelinjakDataHolder;
import com.opgkukic.buzzkeeper.model.SharedViewModel;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class LokacijaFragment extends Fragment implements GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private boolean permissionDenied = false;

    private GoogleMap map;
    private EditText longitude,latitude;
    private AutoCompleteTextView addresField;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private PlacesClient placesClient;
    private SharedViewModel sharedViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lokacija, container, false);

        latitude = view.findViewById(R.id.latitudeEditText);
        longitude = view.findViewById(R.id.longitudeEditText);
        addresField = view.findViewById(R.id.adresaEditText);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), "AIzaSyDlATL_7gd7ibojUsDVY-UxGe2ZGg954xM");
        }

        placesClient = Places.createClient(requireContext());

        addresField.setAdapter(new PlaceAutocompleteAdapter(requireContext(), placesClient));

        addresField.setOnItemClickListener((parent, view1, position, id) -> {
            map.clear();
            AutocompletePrediction prediction = (AutocompletePrediction) parent.getItemAtPosition(position);
            String placeId = prediction.getPlaceId();

            fetchPlaceDetails(placeId);
        });
        latitude.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            sharedViewModel.setLatituda(editable.toString());
            }
        });
longitude.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
sharedViewModel.setLongituda(editable.toString());
    }

});
addresField.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
sharedViewModel.setAdresa(editable.toString());
    }
});
        return view;
    }

    private void fetchPlaceDetails(String placeId) {
        FetchPlaceRequest request = FetchPlaceRequest.newInstance(placeId, Arrays.asList(Place.Field.LAT_LNG, Place.Field.ADDRESS));

        placesClient.fetchPlace(request).addOnSuccessListener(response -> {
            Place place = response.getPlace();
            LatLng latLng = place.getLatLng();
            String address = place.getAddress();

            addresField.setText(address);

            if (latLng != null) {
                if (map != null) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    map.addMarker(new MarkerOptions().position(latLng).title(address));
                }
                latitude.setText(String.valueOf(latLng.latitude));
                longitude.setText(String.valueOf(latLng.longitude));
            }
        }).addOnFailureListener(e -> {

            e.printStackTrace();
            addresField.setText("Greška pri geokodiranju");
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_PERMISSION_REQUEST_CODE)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                if(map != null)
                {
                    onMapReady(map);
                }
            }
        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            map.setMyLocationEnabled(true);

        fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(requireContext());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location ->
        {
            if (location != null) {
                LatLng currentLoaction = new LatLng(location.getLatitude(), location.getLongitude());
                latitude.setText(String.valueOf(location.getLatitude()));
                longitude.setText(String.valueOf(location.getLongitude()));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoaction, 15));
                map.addMarker(new MarkerOptions().position(currentLoaction).title("Vi set ovdje"));
                getAddresFromLoaction(location.getLatitude(), location.getLongitude());
            }

        });
        }
        else
        {
            enableMyLocation();
        }
        map.setOnMapClickListener(latLng ->
        {
            map.clear();
            map.addMarker(new MarkerOptions().position(latLng).title("Kliknuta lokacija"));
            getAddresFromLoaction(latLng.latitude, latLng.longitude);
        });
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment != null && mapFragment.getView() != null)
        {
            View mapView = mapFragment.getView();
            mapView.setOnTouchListener((v, event) ->
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            });
        }
        enableMyLocation();

    }

    private void getAddresFromLoaction(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(requireContext());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String addresText = address.getAddressLine(0);
                addresField.setText(addresText);
            } else {
                addresField.setText("Adresa nije nađena");
            }
        }catch (IOException e)
        {
            e.printStackTrace();
            addresField.setText("Greška pri geokodiranju");
        }
    }

    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_REQUEST_CODE);
    };

    }

    @Override
    public boolean onMyLocationButtonClick() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location ->
            {
                map.clear();
                if (location != null) {
                    LatLng currentLatlang = new LatLng(location.getLatitude(), location.getLongitude());
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatlang, 15));
                    map.addMarker(new MarkerOptions().position(currentLatlang).title("Trenutna lokacija"));
                    getAddresFromLoaction(location.getLatitude(), location.getLongitude());
                }
            }).addOnFailureListener(e ->
            {
                Toast.makeText(getContext(), "Greška pri dohvačanju lokacije", Toast.LENGTH_SHORT).show();
            });


        }
        else{
            enableMyLocation();
        }
        return false;
    }




    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }


}