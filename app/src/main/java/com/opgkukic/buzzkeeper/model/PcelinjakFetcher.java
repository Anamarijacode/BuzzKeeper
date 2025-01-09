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

                // Ispis svih pčelinjaka
                for (Pčelinjak p : pcelinjaci) {
                    System.out.println("Naziv pčelinjaka: " + p.getNazivPčelinjaka());
                    System.out.println("Lokacija: " + p.getLokacija());
                    System.out.println("Datum kreiranja: " + p.getDatumKreiranja());
                    System.out.println("Tip mjesta: " + p.getTipMjesta());
                    System.out.println("Tip pčelinjaka: " + p.getTipPčelinjaka());
                    System.out.println("---------------");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Greška prilikom čitanja iz Firebase-a: " + error.getMessage());
            }
        });
    }
}
