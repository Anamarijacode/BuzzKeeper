package com.opgkukic.buzzkeeper.ui.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.PcelinjakAdapter;
import com.opgkukic.buzzkeeper.adapter.SpinnerPcelinjakAdapter;
import com.opgkukic.buzzkeeper.model.Pčelinjak;
import com.opgkukic.buzzkeeper.ui.fragments.Pcelinjaci.DodajPcelinjak.DodajPcelinjakFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private RecyclerView recyclerView;
    private PcelinjakAdapter adapter;
    private List<Pčelinjak> pcelinjaciList, filteredList;
    private FloatingActionButton dodajPcelinjak;
    private Spinner spinnerFilter;
    private SearchView searchView;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        pcelinjaciList = new ArrayList<>();
        filteredList = new ArrayList<>();
        adapter = new PcelinjakAdapter(filteredList, this);
        recyclerView.setAdapter(adapter);

        dodajPcelinjak = view.findViewById(R.id.fab_add);
        spinnerFilter = view.findViewById(R.id.spinnerFilterPcelinjak);
        searchView = view.findViewById(R.id.searchViewPcelinjak);
        searchView.setQueryHint(getString(R.string.pretrazi));

        String[] nazivi = {"Svi", "Pokretni", "Stacionar"};
        int[] slike = {R.drawable.icon, R.drawable.pokretan, R.drawable.stacionar};
        SpinnerPcelinjakAdapter spinnerAdapter = new SpinnerPcelinjakAdapter(getContext(), nazivi, slike);
        spinnerFilter.setAdapter(spinnerAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterPcelinjaciList(query.trim(), spinnerFilter.getSelectedItem().toString().trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterPcelinjaciList(newText.trim(), spinnerFilter.getSelectedItem().toString().trim());
                return false;
            }
        });

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                filterPcelinjaciList(searchView.getQuery().toString().trim(), parentView.getItemAtPosition(position).toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        dodajPcelinjak.setOnClickListener(view1 -> {
            Fragment newFragment = new DodajPcelinjakFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        ReadData();
        enableMyLocation();
        return view;
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void ReadData() {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) return;

        String userId = user.getUid();
        DatabaseReference pcelinjaci = mDatabase.child("pcelinjaci");
        pcelinjaci.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                pcelinjaciList.clear();
                for (DataSnapshot pcelinjaciSnapshot : snapshot.getChildren()) {
                    Pčelinjak pcelinjak = pcelinjaciSnapshot.getValue(Pčelinjak.class);
                    if (pcelinjak != null && userId.equals(pcelinjak.getUserId())) {
                        pcelinjaciList.add(pcelinjak);
                    }
                }
                filterPcelinjaciList(searchView.getQuery().toString(), spinnerFilter.getSelectedItem().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    private void filterPcelinjaciList(String searchQuery, String selectedTipPcelinjak) {
        filteredList.clear();
        for (Pčelinjak pcelinjak : pcelinjaciList) {
            boolean matchesSearch = searchQuery.isEmpty() ||
                    (pcelinjak.getNazivPčelinjaka() != null &&
                            pcelinjak.getNazivPčelinjaka().toLowerCase().contains(searchQuery.toLowerCase()));

            boolean matchesType = selectedTipPcelinjak.equals("Svi") ||
                    (pcelinjak.getTipPčelinjaka() != null && pcelinjak.getTipPčelinjaka().equalsIgnoreCase(selectedTipPcelinjak));
            if (pcelinjak.getTipPčelinjaka() == null) {
                Log.e(TAG, "Pcelinjak tip is null for: " + pcelinjak.getNazivPčelinjaka());
            }

            if (matchesSearch && matchesType) {
                filteredList.add(pcelinjak);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
