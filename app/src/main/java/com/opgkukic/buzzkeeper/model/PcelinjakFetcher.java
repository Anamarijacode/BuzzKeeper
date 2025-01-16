package com.opgkukic.buzzkeeper.model;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class PcelinjakFetcher {

    public static void main(String[] args) {
        // Referenca na Firebase kolekciju "pčelinjaci"
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("pčelinjaci");

        // Lista za spremanje objekata Pčelinjak
        List<Pčelinjak> pcelinjaci = new ArrayList<>();

        // Čitanje podataka iz Firebase-a
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot pcelinjakSnapshot : snapshot.getChildren()) {
                    // Kreiranje objekta Pčelinjak iz Firebase podataka
                    Pčelinjak pcelinjak = pcelinjakSnapshot.getValue(Pčelinjak.class);
                    if (pcelinjak != null) {
                        pcelinjaci.add(pcelinjak);
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Greška prilikom čitanja iz Firebase-a: " + error.getMessage());
            }
        });
    }
}
