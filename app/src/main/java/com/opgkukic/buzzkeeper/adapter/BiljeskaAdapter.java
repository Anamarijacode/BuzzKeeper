package com.opgkukic.buzzkeeper.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.opgkukic.buzzkeeper.R;
import com.opgkukic.buzzkeeper.model.Bilješka;

import java.util.List;

public class BiljeskaAdapter extends BaseAdapter {

    private Activity activity;
    private List<Bilješka> biljeskeList;
    private OnDeleteListener onDeleteListener;

    public interface OnDeleteListener {
        void onDelete(String bilješkaId);
    }

    public BiljeskaAdapter(Activity activity, List<Bilješka> biljeskeList, OnDeleteListener onDeleteListener) {
        this.activity = activity;
        this.biljeskeList = biljeskeList;
        this.onDeleteListener = onDeleteListener;
    }

    @Override
    public int getCount() {
        return biljeskeList.size();
    }

    @Override
    public Object getItem(int position) {
        return biljeskeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for each item in the list
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_biljeska, parent, false);
        }

        // Get the current Bilješka (note) object
        Bilješka bilješka = biljeskeList.get(position);

        // Bind the data to the views
        TextView tvNaziv = convertView.findViewById(R.id.tvNazivBiljeske);
        TextView tvSadržaj = convertView.findViewById(R.id.tvSadržajBiljeske);
        TextView tvDatum = convertView.findViewById(R.id.tvDatumBiljeske);
        Button btnDelete = convertView.findViewById(R.id.btnDeleteBiljeska);

        tvNaziv.setText(bilješka.getNaziv());
        tvSadržaj.setText(bilješka.getSadržaj());
        tvDatum.setText(bilješka.getDatumKreiranja());

        // Set up the delete button action
        btnDelete.setOnClickListener(v -> {
            if (onDeleteListener != null) {
                onDeleteListener.onDelete(bilješka.getId());  // Pass the Bilješka ID for deletion
            }
        });

        return convertView;
    }
}
