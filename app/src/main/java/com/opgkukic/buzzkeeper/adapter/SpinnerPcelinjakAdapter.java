package com.opgkukic.buzzkeeper.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.opgkukic.buzzkeeper.R;

import java.util.List;

public class SpinnerPcelinjakAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] nazivi;
    private int[] slike;

    public SpinnerPcelinjakAdapter(Context context, String[] nazivi, int[] slike) {
        super(context, R.layout.spinner_item, nazivi);
        this.context = context;
        this.nazivi = nazivi;
        this.slike = slike;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item_pcelinjak, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.spinnerImage);
        TextView textView = convertView.findViewById(R.id.spinnerText);

        imageView.setImageResource(slike[position]);
        textView.setText(nazivi[position]);

        return convertView;
    }
}
