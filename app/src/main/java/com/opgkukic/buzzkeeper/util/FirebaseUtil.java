package com.opgkukic.buzzkeeper.util;


import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtil {
    private static FirebaseDatabase firebaseDatabase;

    // Singleton za FirebaseDatabase instancu
    public static FirebaseDatabase getDatabase() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true); // Omogućava offline rad
        }
        return firebaseDatabase;
    }
}
