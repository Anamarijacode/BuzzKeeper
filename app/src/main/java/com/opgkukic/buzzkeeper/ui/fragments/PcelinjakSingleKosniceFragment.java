package com.opgkukic.buzzkeeper.ui.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

import java.io.IOException;
import java.util.List;


public class PcelinjakSingleKosniceFragment extends Fragment implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private String naziv,tip,userId,tipMjesta,latituda,longituda;
    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private EditText addresField;
    private RecyclerView kosnicaRecicleView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { //DODAJ DA SE NE POMIĆE
        View view = inflater.inflate(R.layout.fragment_pcelinjak_single_kosnice, container, false);
        Bundle args = getArguments();
        addresField = view.findViewById(R.id.editTextAddresField);
        kosnicaRecicleView = view.findViewById(R.id.kosnicaRecyclerView);
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


        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        getAddresFromLoaction(Double.parseDouble(latituda),Double.parseDouble(longituda));
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) { //DODAJ DA SE KARTA NE POMIĆE I DA JE LOKACIJA FIKSNA
        MapsInitializer.initialize(getContext());
        map = googleMap;

        googleMap.getUiSettings().setAllGesturesEnabled(false); //->OVO NAVODNO RADI DA SE KARTA NE POMIĆE
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
        }

    }
}