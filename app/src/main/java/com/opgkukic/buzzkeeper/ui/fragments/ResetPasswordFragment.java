package com.opgkukic.buzzkeeper.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.opgkukic.buzzkeeper.R;


public class ResetPasswordFragment extends Fragment {

private Button btnPromjena;
private EditText emaiPromjena;
private FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
                btnPromjena = view.findViewById(R.id.btnPromjena);
                emaiPromjena = view.findViewById(R.id.etemailPromjena);
                auth = FirebaseAuth.getInstance();
                btnPromjena.setOnClickListener(v->
                {
                    String email = emaiPromjena.getText().toString().trim();
                    if(!TextUtils.isEmpty(email)){
                        resetPassword(email);
                    }
                    else
                    {
                        emaiPromjena.setError("Molimo unesite email");
                    }
                });
        return view;
    }
    private void resetPassword(String email)
    {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(requireActivity(),task ->
        {
            if(task.isSuccessful()){
                Toast.makeText(getContext(), "Email za resetiranje lozinke poslan!", Toast.LENGTH_SHORT).show();
                Fragment newFragment = new LoginFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            else
            {
                Toast.makeText(getContext(), "Nešto je pošlo po zlu. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}