package com.opgkukic.buzzkeeper.ui.fragments.Enciklopedija;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.EnciklopedijaAdapter;
import com.opgkukic.buzzkeeper.model.Enciklopedija;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnciklopedijaFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private RecyclerView recyclerView;
    private EnciklopedijaAdapter adapter;
    private List<Enciklopedija> enciklopedijaList = new ArrayList<>();
    private List<Enciklopedija> filteredList = new ArrayList<>();
    private String selectedCategory = "Sve";  // Trenutno izabrana kategorija
    private String searchQuery = ""; // Ovo će pohranjivati unos iz pretrage

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enciklopedija, container, false);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        recyclerView = view.findViewById(R.id.recyclerviewEnciklopedija);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EnciklopedijaAdapter(filteredList, this);
        recyclerView.setAdapter(adapter);

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery = newText;  // Ažuriramo searchQuery
                filter(searchQuery);  // Pozivamo filter sa novim unosom
                return true;
            }
        });

        // Ovdje dodajemo kod za zatvaranje SearchView kada kliknete izvan njega
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (searchView.hasFocus()) {
                    searchView.clearFocus(); // Zatvaramo SearchView kad korisnik klikne izvan njega
                }
                return false;
            }
        });

        Spinner spinner = view.findViewById(R.id.spinnerFilter);
        List<String> categories = new ArrayList<>();
        categories.add("Sve");
        categories.add("Bolesti");
        categories.add("Uvod");
        categories.add("Početak");
        categories.add("Ispaša");
        categories.add("RH");
        categories.add("Košnice");
        categories.add("LR košnice");
        categories.add("DB košnice");
        categories.add("Pčelinjaci");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedCategory = categories.get(position);
                filterByCategory(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedCategory = "Sve";
                filterByCategory(selectedCategory);
            }
        });

        ReadData();

        return view;
    }


    private void ReadData() {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            Log.w("EnciklopedijaFragment", "Korisnik nije ulogovan.");
            return;
        }

        DatabaseReference enciklopedijaRef = mDatabase.child("enciklopedija");

        enciklopedijaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    Log.w("EnciklopedijaFragment", "Nema podataka u bazi.");
                    return;
                }

                enciklopedijaList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    try {
                        String enciklopedijaId = dataSnapshot.getKey();
                        String naslov = dataSnapshot.child("naziv").getValue(String.class);
                        Object tagsObj = dataSnapshot.child("tags").getValue();
                        String sadrzaj = dataSnapshot.child("sadrzaj").getValue(String.class);
                        if (sadrzaj == null) {
                            sadrzaj = ""; // Ako nema podataka, neka bude prazan string
                        }

                        List<String> tagsLista = new ArrayList<>();
                        if (tagsObj instanceof Map) {
                            Map<String, String> mapaTags = (Map<String, String>) tagsObj;
                            tagsLista.addAll(mapaTags.values());
                        } else if (tagsObj instanceof List) {
                            tagsLista = (List<String>) tagsObj;
                        }

                        Enciklopedija enciklopedija = new Enciklopedija(enciklopedijaId, naslov, tagsLista, sadrzaj);
                        enciklopedijaList.add(enciklopedija);

                    } catch (Exception e) {
                        Log.e("EnciklopedijaFragment", "Greška pri parsiranju podataka", e);
                    }
                }

                Log.d("EnciklopedijaFragment", "Ukupno učitano: " + enciklopedijaList.size());

                filteredList.clear();
                filteredList.addAll(enciklopedijaList);
                filterByCategory(selectedCategory); // Osvežavanje prema selektovanoj kategoriji
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("EnciklopedijaFragment", "Greška pri učitavanju podataka: " + error.getMessage(), error.toException());
            }
        });
    }

    private void filter(String query) {
        filteredList.clear();

        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(enciklopedijaList);
        } else {
            query = query.toLowerCase().trim();

            for (Enciklopedija item : enciklopedijaList) {
                String naslov = item.getNaslov();
                String sadrzajList = item.getSadrzaj();
                List<String> tagsList = item.getTags();

                String sadrzaj = "";
                if (sadrzajList != null) {
                    sadrzaj = String.join("\n", sadrzajList);
                }

                if ((naslov != null && naslov.toLowerCase().contains(query)) ||
                        (sadrzaj != null && sadrzaj.toLowerCase().contains(query)) ||
                        (tagsList != null && containsTag(tagsList, query))) {
                    filteredList.add(item);
                }
            }
        }

        filterByCategory(selectedCategory); // Filtriranje ponovo prema kategoriji
        adapter.notifyDataSetChanged();
    }

    private boolean containsTag(List<String> tagsList, String query) {
        for (String tag : tagsList) {
            if (tag.toLowerCase().contains(query)) {
                return true;
            }
        }
        return false;
    }

    private void filterByCategory(String category) {
        List<Enciklopedija> tempFilteredList = new ArrayList<>();

        if (category.equals("Sve")) {
            tempFilteredList.addAll(enciklopedijaList);
        } else {
            for (Enciklopedija item : enciklopedijaList) {
                List<String> tagsList = item.getTags();
                if (tagsList != null && tagsList.contains(category)) {
                    tempFilteredList.add(item);
                }
            }
        }

        // Ponovno filtriranje prema pretrazi
        List<Enciklopedija> finalFilteredList = new ArrayList<>();
        for (Enciklopedija item : tempFilteredList) {
            if (matchesSearchQuery(item)) {
                finalFilteredList.add(item);
            }
        }

        filteredList.clear();
        filteredList.addAll(finalFilteredList);
        adapter.notifyDataSetChanged();
    }

    private boolean matchesSearchQuery(Enciklopedija item) {
        // Provjera pretrage u naslovu, sadržaju i tagovima
        if (item.getNaslov().toLowerCase().contains(searchQuery) ||
                item.getSadrzaj().toLowerCase().contains(searchQuery) ||
                containsTag(item.getTags(), searchQuery)) {
            return true;
        }
        return false;
    }
}
