package com.opgkukic.buzzkeeper.ui.fragments;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.opgkukic.buzzkeeper.R;

import java.util.Locale;

public class LanguageFragment extends Fragment  {

    private Button btn;
    private Spinner languageSpiner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale("hr");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_language, container, false);

        btn = view.findViewById(R.id.btnZapocnimo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newFragment = new LoginFragment();

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        languageSpiner = view.findViewById(R.id.languageSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.languages, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dopdown_item);
        languageSpiner.setAdapter(adapter);


        languageSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String languageCode = "";
                switch (position) {
                    case 0: // English
                        languageCode = "en";
                        break;
                    case 1: // French
                        languageCode = "hr";
                        break;

                }
                setLocale(languageCode);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ovdje možete obraditi slučaj kada ništa nije selektovano
            }
        });

        return view;
    }
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        Fragment fragment= getParentFragmentManager().findFragmentById(R.id.fragmentId);

        if(fragment != null){
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.detach(fragment);
            transaction.attach(fragment);
            transaction.commit();
        }

    }



}
