package com.opgkukic.buzzkeeper.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.User;
import com.opgkukic.buzzkeeper.ui.activities.HomeActivity;

import java.util.Date;
import java.util.Locale;

public class SingUpFragment extends Fragment {


    private static final String TAG = "nesto";
    private FirebaseAuth auth;
    private Button btnReg;
    private EditText imeR, prezimeR, emailR, lozinkaR, ponovnalozinkaR;
    private String datumKreiranja, odabraniJezik;
    private DatabaseReference mDatabase;
    private boolean isVisible = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sing_up, container, false);
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        imeR = view.findViewById(R.id.etimeR);
        prezimeR = view.findViewById(R.id.etPrezimeR);
        emailR = view.findViewById(R.id.etemailR);
        lozinkaR = view.findViewById(R.id.etlozinkaR);
        ponovnalozinkaR = view.findViewById(R.id.etponovnalozinka);
        btnReg = view.findViewById(R.id.btnReg);
        btnReg.setOnClickListener(v-> registerNeUser());

       lozinkaR.setOnTouchListener((v, event)->
        {
            if(event.getAction() == MotionEvent.ACTION_UP)
            {
                int drawableEnd = lozinkaR.getCompoundDrawables()[2].getBounds().width();
                if(event.getRawX() >= (lozinkaR.getRight() - drawableEnd - lozinkaR.getPaddingEnd())){
                    if(isVisible)
                    {
                       lozinkaR.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                       lozinkaR.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_off,0);
                    }
                    else{
                        lozinkaR.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        lozinkaR.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_on,0);
                    }
                    isVisible= !isVisible;
                    lozinkaR.setSelection(lozinkaR.getText().length());
                    return true;
                }
            }
            return false;
        });
       ponovnalozinkaR.setOnTouchListener((v, event)->
        {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int drawableEnd = ponovnalozinkaR.getCompoundDrawables()[2].getBounds().width();
                if (event.getRawX() >= (ponovnalozinkaR.getRight() - drawableEnd - ponovnalozinkaR.getPaddingEnd())) {
                    if (isVisible) {
                        ponovnalozinkaR.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        ponovnalozinkaR.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0);
                    } else {
                        ponovnalozinkaR.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                       ponovnalozinkaR.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_on, 0);
                    }
                    isVisible = !isVisible;
                  ponovnalozinkaR.setSelection(ponovnalozinkaR.getText().length());
                    return true;
                }
            }
            return false;
        });
        return view;

    }
    public void registerNeUser()
    {
        String email1, lozinka, ime1, prezime1;
        email1 = emailR.getText().toString();
        lozinka = lozinkaR.getText().toString();
        ime1= imeR.getText().toString();
        prezime1 = prezimeR.getText().toString();
        Date objDate = new Date();
        datumKreiranja = objDate.toString();
        if(requireContext().getResources().getConfiguration().getLocales().get(0).getLanguage() == "")
        {
            odabraniJezik = "en";
        }
        else
        {
            odabraniJezik = requireContext().getResources().getConfiguration().getLocales().get(0).getLanguage();
        }
        Log.d(TAG, "registerNeUser: " + odabraniJezik);
       if(!email1.isEmpty() && !lozinka.isEmpty() && !ime1.isEmpty() && !prezime1.isEmpty())
       {
           auth.createUserWithEmailAndPassword(email1, lozinka).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       FirebaseUser user = auth.getCurrentUser();
                       if(user != null)
                       {
                           String userId = user.getUid();
                           User newUser = new User(datumKreiranja, email1, ime1, "", odabraniJezik, prezime1, "", "");
                           mDatabase.child(userId).setValue(newUser).addOnCompleteListener(dbTask ->
                           {
                               if (dbTask.isSuccessful())
                               {
                                   startActivity(new Intent(getContext(), HomeActivity.class));
                                   requireActivity().finish();
                               }
                               else
                               {
                                   Toast.makeText(getContext(), "Neuspješna registracija registracija", Toast.LENGTH_SHORT).show();
                               }
                           });
                       }
                   }
                   else
                   {
                       Toast.makeText(getContext(), "Neuspješna registracija registracija", Toast.LENGTH_SHORT).show();
                   }
               }
           });
       }
    }
}