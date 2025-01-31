package com.opgkukic.buzzkeeper.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.opgkukic.buzzkeeper.model.Košnica;

import java.util.List;

public class KosniceRecyclerView extends RecyclerView.Adapter<KosniceRecyclerView.KosnicaViewHolder> {

    private List<Košnica> Kosnice;

    public KosniceRecyclerView(List<Košnica> kosnice) {
        Kosnice = kosnice;
    }

    @Override
    public KosnicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(KosnicaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return Kosnice == null ? 0 : Kosnice.size();

    }

    public static class KosnicaViewHolder extends RecyclerView.ViewHolder {
        public KosnicaViewHolder(View itemView) {
            super(itemView);
        }
    }

}
