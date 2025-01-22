package com.opgkukic.buzzkeeper.ui.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.opgkukic.buzzkeeper.R;

import org.checkerframework.checker.nullness.qual.NonNull;


public class PcelinjakSingleKosniceFragment extends Fragment implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private String naziv,tip,userId,tipMjesta,latituda,longituda;
    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pcelinjak_single_kosnice, container, false);
        Bundle args = getArguments();
        if (args != null) {
           naziv = args.getString("nazivPčelinjaka");
            tip = args.getString("tipPčelinjaka");
            userId = args.getString("userId");
            latituda = args.getString("latituda");
          longituda = args.getString("longituda");
          tipMjesta = args.getString("tipMjesta");

            Log.d("PcelinjakSingleKosnice", "Naziv: " + naziv);
            Log.d("PcelinjakSingleKosnice", "Tip: " + tip);
            Log.d("PcelinjakSingleKosnice", "Lat: " + latituda);
            Log.d("PcelinjakSingleKosnice", "Long: " + longituda);
        }

        else if(args == null)
        {
            Log.d("PcelinjakData", "Nill je  " );
        }

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        map = googleMap;

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    LatLng currentLocation = new LatLng(Double.parseDouble(latituda), Double.parseDouble(longituda));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                    map.addMarker(new MarkerOptions().position(currentLocation).title(naziv));
                }
            });
        } else {
            enableMyLocation();
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
}