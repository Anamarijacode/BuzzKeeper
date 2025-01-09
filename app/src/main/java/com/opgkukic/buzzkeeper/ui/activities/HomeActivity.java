package com.opgkukic.buzzkeeper.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opgkukic.buzzkeeper.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.opgkukic.buzzkeeper.model.Pčelinjak;
import com.opgkukic.buzzkeeper.ui.fragments.EnciklopedijaFragment;
import com.opgkukic.buzzkeeper.ui.fragments.ForumFragment;
import com.opgkukic.buzzkeeper.ui.fragments.HomeFragment;
import com.opgkukic.buzzkeeper.ui.fragments.OrganizatorFragment;
import com.opgkukic.buzzkeeper.ui.fragments.ProfilFragment;
import com.opgkukic.buzzkeeper.ui.fragments.ResetPasswordFragment;
import com.opgkukic.buzzkeeper.ui.fragments.SingUpFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton fabMid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        FirebaseApp.initializeApp(this);
        fabMid = findViewById(R.id.fab_middle);

        try {
            FirebaseApp.initializeApp(this);
            Log.d("Firebase", "Firebase je uspješno inicijaliziran.");
        } catch (Exception e) {
            Log.e("Firebase", "Greška pri inicijalizaciji Firebase-a: " + e.getMessage());
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
        bottomNavigationView.setOnItemSelectedListener(v->
        {
            Fragment selectedFragment = null;
            switch (v.getItemId())
            {
                case R.id.Organizator:
                    selectedFragment= new OrganizatorFragment();
                    break;
                case R.id.Forum:
                    selectedFragment = new ForumFragment();
                    break;
                case R.id.Enciklopedija:
                    selectedFragment = new EnciklopedijaFragment();
                    break;
                case R.id.Profil:
                    selectedFragment = new ProfilFragment();
                    break;

            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
       fabMid.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Fragment newFragment = new HomeFragment();
               FragmentManager fragmentManager = getSupportFragmentManager();
               FragmentTransaction transaction = fragmentManager.beginTransaction();
               transaction.replace(R.id.fragment_container, newFragment);
               transaction.addToBackStack(null);
               transaction.commit();
           }
       });
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerViewPcelinjaci);
//        Log.d("HomeActivity", "Pozivam loadPcelinjaciFromFirebase metodu");
//        loadPcelinjaciFromFirebase(recyclerView);


    }
//    private void loadPcelinjaciFromFirebase(RecyclerView recyclerView) {
//        Log.d("Poziv", "Pozivam loadPcelinjaciFromFirebase metodu");
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("pcelinjaci");
//        Log.d("FirebaseDatabase", "DatabaseReference: " + ref.toString()); // Logiraj DatabaseReference objekt
//
//
//        List<Pčelinjak> pcelinjaci = new ArrayList<>();
//
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                Log.d("Firebase", "Početak dohvaćanja podataka...");
//
//                for (DataSnapshot pcelinjakSnapshot : snapshot.getChildren()) {
//                    Log.d("Firebase", "Obradujem pčelinjak: " + pcelinjakSnapshot.getKey());
//                    Pčelinjak pcelinjak = pcelinjakSnapshot.getValue(Pčelinjak.class);
//
//                    if (pcelinjak != null) {
//                        pcelinjaci.add(pcelinjak);
//                        Log.d("Firebase", "Dohvaćen pčelinjak: "
//                                + pcelinjak.getNazivPčelinjaka() + ", Tip: "
//                                + pcelinjak.getTipMjesta());
//                    } else {
//                        Log.e("Firebase", "Pčelinjak je null za snapshot: " + pcelinjakSnapshot.toString());
//                    }
//                }
//
//                Log.d("Firebase", "Ukupan broj dohvaćenih pčelinjaka: " + pcelinjaci.size());
//
//                // Postavite adapter nakon što su podaci učitani
//                PcelinjakAdapter adapter = new PcelinjakAdapter(HomeActivity.this, pcelinjaci);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                Log.e("Firebase", "Greška prilikom dohvaćanja podataka: " + error.getMessage());
//                Toast.makeText(getApplicationContext(), "Greška prilikom dohvaćanja podataka!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}

