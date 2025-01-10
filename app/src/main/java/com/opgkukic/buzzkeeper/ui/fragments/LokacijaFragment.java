package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.database.annotations.Nullable;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.PcelinjakDataHolder;


public class LokacijaFragment extends Fragment implements OnMapReadyCallback{

    private EditText etLatitude, etLongitude;
//    private static final int DIALOG_REQUEST = 9001;
//
//    public GoogleMap mMap;
//
//    public void onMapReady(GoogleMap map) {      mMap = map;
//
//        if (servicesOK() /*&& initMap()*/) {
//
//            //Geocoder gc = new Geocoder(this);
//            //List<Address> list;
//            try {
//                //list = gc.getFromLocationName(hotel.getAddress(), 1);
//                //Address address = list.get(0);
//
//                double lat = 0.6856979;
//                double lng = 16.8795434;
//                LatLng latLong = new LatLng(lat, lng);
////                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLong, 5);
//                Log.i("BEGINNING","Check this");
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLong));
//                Log.i("Finishing","Check this");
//
//                /*MarkerOptions options = new MarkerOptions()
//                        .title(getString(R.string.landon_hotel) + ", " + city)
//                        .position(new LatLng(lat, lng));
//                mMap.addMarker(options);*/
//                //onMapReady(mMap);
//
//            } /*catch (IOException e) {
//                Toast.makeText(this, getString(R.string.error_finding_hotel), Toast.LENGTH_SHORT).show();
//            }*/ catch (Exception e) {
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("Check this->", e.getMessage());
//            }
//
//
//
//        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lokacija, container, false);
//        etLatitude = view.findViewById(R.id.etLatitude);
//        etLongitude = view.findViewById(R.id.etLongitude);
//        view.findViewById(R.id.nextButton).setOnClickListener(v -> {
//            PcelinjakDataHolder.latitude = Double.parseDouble(etLatitude.getText().toString());
//            PcelinjakDataHolder.longitude = Double.parseDouble(etLongitude.getText().toString());
//            ((DodajPcelinjakFragment) getParentFragment()).viewPager.setCurrentItem(2);
//        });
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
//        public boolean servicesOK() {
//            int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
//
//            if (result == ConnectionResult.SUCCESS) {
//                return true;
//            } else if (GooglePlayServicesUtil.isUserRecoverableError(result)) {
//                Dialog dialog = GooglePlayServicesUtil.getErrorDialog(result, this, DIALOG_REQUEST);
//                dialog.show();
//            } else {
//                Toast.makeText(this, getString(R.string.error_connect_to_services), Toast.LENGTH_LONG).show();
//            }
//            return false;
//        }
//        https://stackoverflow.com/questions/49009942/android-google-maps-not-loading-the-map
}