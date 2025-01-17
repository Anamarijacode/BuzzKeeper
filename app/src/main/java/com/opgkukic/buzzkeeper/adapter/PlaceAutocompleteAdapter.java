package com.opgkukic.buzzkeeper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.firebase.database.annotations.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PlaceAutocompleteAdapter extends ArrayAdapter<AutocompletePrediction> {

    private final PlacesClient placesClient;
    private List<AutocompletePrediction> resultList = new ArrayList<>();

    public PlaceAutocompleteAdapter(Context context, PlacesClient placesClient) {
        super(context, android.R.layout.simple_dropdown_item_1line);
        this.placesClient = placesClient;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public AutocompletePrediction getItem(int position) {
        return resultList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }
        AutocompletePrediction item = getItem(position);
        ((TextView) convertView).setText(item != null ? item.getPrimaryText(null) : "");
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null) {
                    FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                            .setQuery(constraint.toString())
                            .build();

                    placesClient.findAutocompletePredictions(request).addOnSuccessListener(response -> {
                        resultList = response.getAutocompletePredictions();
                        results.values = resultList;
                        results.count = resultList.size();
                        notifyDataSetChanged();
                    }).addOnFailureListener(Throwable::printStackTrace);
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Already handled in performFiltering
            }
        };
    }
}
