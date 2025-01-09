package com.opgkukic.buzzkeeper.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.Pčelinjak;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class PcelinjakAdapter extends RecyclerView.Adapter<PcelinjakAdapter.PcelinjakViewHolder> {


    private List<Pčelinjak> pcelinjaci;

    public PcelinjakAdapter(List<Pčelinjak> pcelinjaci) {
        this.pcelinjaci = pcelinjaci;
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
        // Postavljanje slike na temelju tipa pčelinjaka
        if ("stacionar".equalsIgnoreCase(pcelinjak.getTipPčelinjaka())) {
            holder.pcelinjakImageView.setImageResource(R.drawable.stacionar); // Ikonica za stacionar
        } else if ("pokretni".equalsIgnoreCase(pcelinjak.getTipPčelinjaka())) {
            holder.pcelinjakImageView.setImageResource(R.drawable.pokretni); // Ikonica za pokretni
        }
    }

    @Override
    public int getItemCount() {
        return pcelinjaci == null ? 0 : pcelinjaci.size();
    }

    public static class PcelinjakViewHolder extends RecyclerView.ViewHolder {
        TextView nazivTextView, tipTextView;
        ImageView pcelinjakImageView;

        public PcelinjakViewHolder(@NonNull View itemView) {
            super(itemView);
            nazivTextView = itemView.findViewById(R.id.nazivPcelinjakaTextView);
            pcelinjakImageView = itemView.findViewById(R.id.pcelinjakImageView);
        }
    }
}
