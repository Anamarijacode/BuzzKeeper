package com.opgkukic.buzzkeeper.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.Pčelinjak;
import com.opgkukic.buzzkeeper.ui.activities.PcelinjakSingleActivity;
import com.opgkukic.buzzkeeper.ui.fragments.DodajKosnicuView2Fragment;
import com.opgkukic.buzzkeeper.ui.fragments.PcelinjakSingleFragment;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class PcelinjakAdapter extends RecyclerView.Adapter<PcelinjakAdapter.PcelinjakViewHolder> {

    private List<Pčelinjak> pcelinjaci;
    private Fragment parentFragment; // Dodano za pristup FragmentManager-u

    public PcelinjakAdapter(List<Pčelinjak> pcelinjaci, Fragment parentFragment) {
        this.pcelinjaci = pcelinjaci;
        this.parentFragment = parentFragment;
    }

    @NonNull
    @Override
    public PcelinjakViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pcelinjak, parent, false);
        return new PcelinjakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PcelinjakViewHolder holder, int position) {
        Pčelinjak pcelinjak = pcelinjaci.get(position);
        holder.nazivTextView.setText(pcelinjak.getNazivPčelinjaka());

        // Set image based on the type of beehive
        if ("stacionar".equalsIgnoreCase(pcelinjak.getTipPčelinjaka())) {
            holder.pcelinjakImageView.setImageResource(R.drawable.kosnica);
        } else if ("pokretni".equalsIgnoreCase(pcelinjak.getTipPčelinjaka())) {
            holder.pcelinjakImageView.setImageResource(R.drawable.pokretan);
        }

        // On item click, navigate to PcelinjakSingleFragment
        holder.itemView.setOnClickListener(v -> {
            Fragment newFragment = new PcelinjakSingleFragment();
            Bundle bundle = new Bundle();
            bundle.putString("nazivPčelinjaka", pcelinjak.getNazivPčelinjaka());
            bundle.putString("tipPčelinjaka", pcelinjak.getTipPčelinjaka());
            bundle.putString("tipMjesta", pcelinjak.getTipMjesta());
            bundle.putString("latituda", pcelinjak.getLokacija().getLatitude());
            bundle.putString("longituda", pcelinjak.getLokacija().getLongitude());
            bundle.putString("userId", pcelinjak.getUserId());
            newFragment.setArguments(bundle);

            FragmentManager fragmentManager = parentFragment.getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        // On "Dodaj" button click, open DodajKosnicuView2Fragment
        holder.addHiveButton.setOnClickListener(v -> {
            Fragment newFragment = new DodajKosnicuView2Fragment();
            FragmentManager fragmentManager = parentFragment.getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }



    @Override
    public int getItemCount() {
        return pcelinjaci == null ? 0 : pcelinjaci.size();
    }

    public static class PcelinjakViewHolder extends RecyclerView.ViewHolder {
        TextView nazivTextView;
        ImageView pcelinjakImageView;
        Button addHiveButton; // Reference to the "Dodaj" button

        public PcelinjakViewHolder(@NonNull View itemView) {
            super(itemView);
            nazivTextView = itemView.findViewById(R.id.nazivPcelinjakaTextView);
            pcelinjakImageView = itemView.findViewById(R.id.pcelinjakImageView);
            addHiveButton = itemView.findViewById(R.id.addHiveButton); // Initialize the button
        }
    }

}