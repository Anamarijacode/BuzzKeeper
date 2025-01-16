package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.PcelinjakDataHolder;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NazivFragment extends Fragment {
    private EditText nazivEditText, datumEditText;
    private Button submitButton;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_naziv, container, false);
        datumEditText = view.findViewById(R.id.datumKreiranjaEditText);
        String currentDate = getCurrentDate();
        datumEditText.setText(currentDate);
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("SELECT A DATE");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();
       datumEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getParentFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String date = sdf.format(new Date(selection));
                datumEditText.setText(date);

            }
        });

//        nazivEditText = view.findViewById(R.id.nazivPcelinjakaEditText);
//        datumEditText = view.findViewById(R.id.datumKreiranjaEditText);
//        submitButton = view.findViewById(R.id.submitButton);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("pcelinjaci");
//
//        submitButton.setOnClickListener(v -> {
//            String naziv = nazivEditText.getText().toString().trim();
//            String datum = datumEditText.getText().toString().trim();
//
//            if (naziv.isEmpty() || datum.isEmpty()) {
//                Toast.makeText(getContext(), "Molimo unesite sve podatke!", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            String pcelinjakId = databaseReference.push().getKey();
//            PcelinjakDataHolder pcelinjak = new PcelinjakDataHolder(pcelinjakId, naziv, datum, null, null, null, null);
//            databaseReference.child(pcelinjakId).setValue(pcelinjak)
//                    .addOnSuccessListener(aVoid -> Toast.makeText(getContext(), "Podaci spremljeni!", Toast.LENGTH_SHORT).show())
//                    .addOnFailureListener(e -> Toast.makeText(getContext(), "Greška pri spremanju podataka.", Toast.LENGTH_SHORT).show());
//        });

        return view;
    }
    private String getCurrentDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return sdf.format(new Date());

    }
}
