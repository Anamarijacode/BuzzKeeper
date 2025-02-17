package com.opgkukic.buzzkeeper.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.opgkukic.buzzkeeper.model.Košnica;

public class FirebaseService {
    private DatabaseReference databaseReference;

    public FirebaseService() {
        databaseReference = FirebaseDatabase.getInstance().getReference("kosnice");
    }

    public void dodajKosnicu(Košnica kosnica) {
        String kosnicaId = databaseReference.push().getKey();
        if (kosnicaId != null) {
            databaseReference.child(kosnicaId).setValue(kosnica);
        }
    }
}
