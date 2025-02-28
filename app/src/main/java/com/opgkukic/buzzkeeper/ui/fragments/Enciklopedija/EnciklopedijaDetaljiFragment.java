package com.opgkukic.buzzkeeper.ui.fragments.Enciklopedija;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opgkukic.buzzkeeper.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.airbnb.lottie.LottieAnimationView;

public class EnciklopedijaDetaljiFragment extends Fragment {

    private PDFView pdfView;
    private DatabaseReference mDatabase;
    private LottieAnimationView beeAnimation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflating the layout
        View view = inflater.inflate(R.layout.fragment_enciklopedija_detalji, container, false);

        // Initializing views
        pdfView = view.findViewById(R.id.pdfView);
        beeAnimation = view.findViewById(R.id.beeanimation);

        // Set up Lottie animation
        beeAnimation.loop(true);
        beeAnimation.playAnimation();
        beeAnimation.setVisibility(View.VISIBLE);  // Ensure it's visible initially

        // Setting transparent background
        view.setBackgroundColor(android.graphics.Color.TRANSPARENT);

        // Get the encyclopedia ID from arguments
        String enciklopedijaId = getArguments().getString("idEnciklopedija", "");
        if (!enciklopedijaId.isEmpty()) {
            loadPdfFromFirebase(enciklopedijaId);  // Load PDF using the ID
        } else {
            Log.e("EnciklopedijaDetalji", "ID enciklopedije nije pronađen.");
        }

        return view;
    }

    private void loadPdfFromFirebase(String enciklopedijaId) {
        mDatabase = FirebaseDatabase.getInstance().getReference("enciklopedija");

        // Fetch PDF URL from Firebase
        mDatabase.child(enciklopedijaId).child("url").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String pdfUrl = snapshot.getValue(String.class);
                    if (pdfUrl != null && !pdfUrl.isEmpty()) {
                        downloadAndDisplayPdf(pdfUrl);  // Download and display the PDF
                    } else {
                        Log.e("EnciklopedijaDetalji", "PDF URL je prazan za enciklopediju ID: " + enciklopedijaId);
                    }
                } else {
                    Log.e("EnciklopedijaDetalji", "PDF URL nije pronađen u bazi za ID: " + enciklopedijaId);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("EnciklopedijaDetalji", "Greška pri učitavanju podataka iz baze: " + error.getMessage());
            }
        });
    }

    private void downloadAndDisplayPdf(String pdfUrl) {
        new Thread(() -> {
            try {
                // Download PDF file from URL
                URL url = new URL(pdfUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                File pdfFile = new File(getActivity().getCacheDir(), "downloaded.pdf");
                FileOutputStream outputStream = new FileOutputStream(pdfFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }

                outputStream.close();
                inputStream.close();

                // When PDF is loaded, hide animation and show PDF with delay
                getActivity().runOnUiThread(() -> {
                    // Delay before hiding the animation and showing the PDF
                    new Handler().postDelayed(() -> {
                        beeAnimation.cancelAnimation();
                        beeAnimation.setVisibility(View.GONE);

                        pdfView.setVisibility(View.VISIBLE);  // Show PDF viewer
                        pdfView.fromFile(pdfFile)
                                .enableSwipe(true)
                                .swipeHorizontal(false)
                                .enableDoubletap(true)
                                .load();
                    }, 30);  // Delay of 500ms (0.5 seconds)

                });

            } catch (Exception e) {
                Log.e("EnciklopedijaDetalji", "Greška pri preuzimanju PDF-a: " + e.getMessage());

                // If error occurs, hide the animation
                getActivity().runOnUiThread(() -> {
                    beeAnimation.cancelAnimation();
                    beeAnimation.setVisibility(View.GONE);
                });
            }
        }).start();
    }
}
