package com.opgkukic.buzzkeeper.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.ui.activities.HomeActivity;

public class LoginFragment extends Fragment {

    private Button btn;
    private FirebaseAuth auth;
    private EditText emailEdit, lozinkaEdit;
    private TextView linkReg, lozinkaZab;
    private boolean isVisible = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        auth = FirebaseAuth.getInstance();
        emailEdit = view.findViewById(R.id.etemail);
        lozinkaEdit= view.findViewById(R.id.etlozinka);
        btn = view.findViewById(R.id.btnPrijava);
        linkReg = view.findViewById(R.id.tvLinkReg);
        lozinkaZab= view.findViewById(R.id.tvLinkZaboravljenLozink);
        lozinkaEdit.setOnTouchListener((v, event)->
        {
            if(event.getAction() == MotionEvent.ACTION_UP)
            {
                int drawableEnd = lozinkaEdit.getCompoundDrawables()[2].getBounds().width();
                if(event.getRawX() >= (lozinkaEdit.getRight() - drawableEnd - lozinkaEdit.getPaddingEnd())){
                    if(isVisible)
                    {
                        lozinkaEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        lozinkaEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_off,0);
                    }
                    else{
                        lozinkaEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        lozinkaEdit.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_visibility_on,0);
                    }
                    isVisible= !isVisible;
                    lozinkaEdit.setSelection(lozinkaEdit.getText().length());
                    return true;
                }
            }
            return false;
        });
linkReg.setOnClickListener(v->
{
    Fragment newFragment = new SingUpFragment();
    FragmentManager fragmentManager = getParentFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.fragment_container, newFragment);
    transaction.addToBackStack(null);
    transaction.commit();
});
lozinkaZab.setOnClickListener(v->
{
    Fragment newFragment = new ResetPasswordFragment();
    FragmentManager fragmentManager = getParentFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.fragment_container, newFragment);
    transaction.addToBackStack(null);
    transaction.commit();
});

  btn.setOnClickListener(v-> loginUser());
        return view;
    }
    private void loginUser()
    {
        String email = emailEdit.getText().toString();
        String lozinka= lozinkaEdit.getText().toString();
        if(!email.isEmpty() && !lozinka.isEmpty())
        {
            auth.signInWithEmailAndPassword(email, lozinka).
                    addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getContext(), HomeActivity.class));
                            requireActivity().finish();
                        }
                    else{
                            Toast.makeText(getContext(),"@string/neuspjesna: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    });

        }
        else if(email.isEmpty())
        {
            Toast.makeText(getContext(), "@string/uemail", Toast.LENGTH_SHORT).show();
        }
        else if(lozinka.isEmpty())
        {
            Toast.makeText(getContext(), "@string/ulozinka", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getContext(),"@string/email_lozinka", Toast.LENGTH_SHORT).show();
        }
    }
}
