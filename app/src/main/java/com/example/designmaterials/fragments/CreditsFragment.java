package com.example.designmaterials.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.designmaterials.javabeans.Attribution;
import com.example.designmaterials.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditsFragment extends Fragment {


    public CreditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credits, container, false);

        ArrayList<Attribution> attributions = new ArrayList<>();

        attributions.add(new Attribution("Designer", "Ndicirm Strazimiri", "''"));
        attributions.add(new Attribution("Idea Man", "Abel Anderson", "''"));



        ((ListView) view.findViewById(R.id.creditListView)).setAdapter(new creditListViewAdapter(getContext(), attributions));

        return view;
    }

    public class creditListViewAdapter extends ArrayAdapter<Attribution> {

        public creditListViewAdapter(@NonNull Context context, ArrayList<Attribution> resource) {
            super(context, 0, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Attribution item = getItem(position);

            if(convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_credit, parent, false);
            }

            ((TextView) convertView.findViewById(R.id.creditTitle)).setText(item.getTitle());
            ((TextView) convertView.findViewById(R.id.creditName)).setText(item.getName());
            ((TextView) convertView.findViewById(R.id.creditDescription)).setText(item.getDescription());

            return convertView;
        }
    }

}
