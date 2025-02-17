package com.opgkukic.buzzkeeper.ui.fragments.Organizator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.adapter.BiljeskaAdapter;
import com.opgkukic.buzzkeeper.model.Bilješka;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BiljeskeFragment extends Fragment {

    private ListView listViewBiljeske;
    private Button btnAddBiljeska;
    private DatabaseReference biljeskeRef;
    private List<Bilješka> biljeskeList;

    public BiljeskeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_biljeske2, container, false);

        listViewBiljeske = view.findViewById(R.id.listViewBiljeske);
        btnAddBiljeska = view.findViewById(R.id.btnAddBiljeska);

        biljeskeRef = FirebaseDatabase.getInstance().getReference("biljeske");
        biljeskeList = new ArrayList<>();

        // Set up add button click listener
        btnAddBiljeska.setOnClickListener(v -> {
            showAddBilješkaDialog();
        });

        // Load notes from Firebase
        loadBiljeske();

        return view;
    }

    public void loadBiljeske() {
        biljeskeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                biljeskeList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Bilješka bilješka = snapshot.getValue(Bilješka.class);
                    bilješka.setId(snapshot.getKey());  // Set the unique ID of the note
                    biljeskeList.add(bilješka);
                }

                // Set the adapter to the ListView
                BiljeskaAdapter adapter = new BiljeskaAdapter(getActivity(), biljeskeList, new BiljeskaAdapter.OnDeleteListener() {
                    @Override
                    public void onDelete(String bilješkaId) {
                        deleteBilješka(bilješkaId);
                    }
                });
                listViewBiljeske.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(getContext(), "Error loading notes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to delete a Bilješka (Note) from Firebase
    public void deleteBilješka(String bilješkaId) {
        biljeskeRef.child(bilješkaId).removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Bilješka deleted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Failed to delete Bilješka!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Method to add a new Bilješka (Note) to Firebase
    public void addBilješka(Bilješka bilješka) {
        String bilješkaId = biljeskeRef.push().getKey();
        if (bilješkaId != null) {
            bilješka.setId(bilješkaId);
            biljeskeRef.child(bilješkaId).setValue(bilješka)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Bilješka added!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Failed to add Bilješka!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    public void showAddBilješkaDialog() {
        // Create an AlertDialog to allow users to input Bilješka details
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Bilješka");

        // Set up the input fields for Bilješka
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        // Input field for Naziv
        final EditText inputNaziv = new EditText(getContext());
        inputNaziv.setHint("Naziv");
        layout.addView(inputNaziv);

        // Input field for Sadržaj
        final EditText inputSadržaj = new EditText(getContext());
        inputSadržaj.setHint("Sadržaj");
        layout.addView(inputSadržaj);

        // Input field for DatumKreiranja (set the date automatically)
        final EditText inputDatumKreiranja = new EditText(getContext());
        inputDatumKreiranja.setHint("Datum Kreiranja (YYYY-MM-DD)");
        inputDatumKreiranja.setText(getCurrentDate()); // Use current date
        layout.addView(inputDatumKreiranja);

        // Create the dialog and set the layout
        builder.setView(layout);

        // Set up the dialog buttons
        builder.setPositiveButton("Add", (dialog, which) -> {
            // Collect the input values
            String naziv = inputNaziv.getText().toString();
            String sadržaj = inputSadržaj.getText().toString();
            String datumKreiranja = inputDatumKreiranja.getText().toString();
            String relatedId = "pčelinjakId1";  // You can set this dynamically if needed

            // Create a new Bilješka object
            Bilješka bilješka = new Bilješka(datumKreiranja, naziv, relatedId, sadržaj);

            // Add the Bilješka to Firebase
            addBilješka(bilješka);

            // Dismiss the dialog
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Dismiss the dialog
            dialog.dismiss();
        });

        // Show the dialog
        builder.show();
    }

    // Helper method to get current date (you can adjust the format as needed)
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());
    }

}
