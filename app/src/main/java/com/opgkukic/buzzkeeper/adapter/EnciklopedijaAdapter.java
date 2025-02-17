package com.opgkukic.buzzkeeper.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.Enciklopedija;
import com.opgkukic.buzzkeeper.ui.fragments.Enciklopedija.EnciklopedijaDetaljiFragment;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class EnciklopedijaAdapter extends RecyclerView.Adapter<EnciklopedijaAdapter.EnciklopedijaViewHolder> {
    private List<Enciklopedija> enciklopedije;
    private Fragment parentFragment;

    public EnciklopedijaAdapter(List<Enciklopedija> enciklopedije, Fragment parentFragment) {
        this.enciklopedije = enciklopedije != null ? enciklopedije : new ArrayList<>();
        this.parentFragment = parentFragment;
    }

    @NotNull
    @Override
    public EnciklopedijaViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.enciklopedia_item, parent, false);
        return new EnciklopedijaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EnciklopedijaViewHolder holder, int position) {
        Enciklopedija enciklopedija = enciklopedije.get(position);
        holder.naslov.setText(enciklopedija.getNaslov());

        if (enciklopedija.getSadrzaj() != null && !enciklopedija.getSadrzaj().isEmpty()) {
            holder.sadrzaj.setText(String.join("\n\n", enciklopedija.getSadrzaj()));
        } else {
            holder.sadrzaj.setText("Nema dostupnog sadržaja.");
        }

        holder.itemView.setOnClickListener(v -> {
            EnciklopedijaDetaljiFragment newFragment = new EnciklopedijaDetaljiFragment();
            Bundle bundle = new Bundle();
            bundle.putString("naziv", enciklopedija.getNaslov());
            bundle.putStringArrayList("sadrzaj", new ArrayList<>(enciklopedija.getSadrzaj()));
            bundle.putStringArrayList("literatura", new ArrayList<>(enciklopedija.getLiteratura()));
            newFragment.setArguments(bundle);

            FragmentManager fragmentManager = parentFragment.getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return enciklopedije.size();
    }

    public static class EnciklopedijaViewHolder extends RecyclerView.ViewHolder {
        TextView naslov, sadrzaj;

        public EnciklopedijaViewHolder(@NotNull View itemView) {
            super(itemView);
            naslov = itemView.findViewById(R.id.naslov);
            sadrzaj = itemView.findViewById(R.id.tekst);
        }
    }
}
