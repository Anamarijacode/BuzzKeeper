package com.opgkukic.buzzkeeper.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.Pčelinjak;
import com.opgkukic.buzzkeeper.ui.activities.PcelinjakSingleActivity;
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

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), PcelinjakSingleActivity.class);

            // Add data to the intent
            intent.putExtra("nazivPcelinjaka", pcelinjak.getNazivPčelinjaka());
            intent.putExtra("tipPcelinjaka", pcelinjak.getTipPčelinjaka());
            intent.putExtra("tipMjesta", pcelinjak.getTipMjesta());
            intent.putExtra("latituda", pcelinjak.getLokacija().getLatitude());
            intent.putExtra("longituda", pcelinjak.getLokacija().getLongitude());
            intent.putExtra("userId", pcelinjak.getUserId());

            // Start the activity
            v.getContext().startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        return pcelinjaci == null ? 0 : pcelinjaci.size();
    }

    public static class PcelinjakViewHolder extends RecyclerView.ViewHolder {
        TextView nazivTextView;
        ImageView pcelinjakImageView;

        public PcelinjakViewHolder(@NonNull View itemView) {
            super(itemView);
            nazivTextView = itemView.findViewById(R.id.nazivPcelinjakaTextView);
            pcelinjakImageView = itemView.findViewById(R.id.pcelinjakImageView);
        }
    }
}