package com.example.designmaterials.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        final ArrayList<Attribution> attributions = new ArrayList<>();

        attributions.add(new Attribution(getString(R.string.creditNdricimTitle), getString(R.string.creditNdricimName), getString(R.string.creditGithubLinkName), getString(R.string.creditNdricimLink)));
        attributions.add(new Attribution(getString(R.string.creditAbelTitle), getString(R.string.creditAbelName), getString(R.string.creditGithubLinkName), getString(R.string.creditAbelLink)));

        ListView listView = view.findViewById(R.id.creditListView);
        listView.setAdapter(new creditListViewAdapter(getContext(), attributions));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(attributions.get(position).getLink()));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


        return view;
    }

    public class creditListViewAdapter extends ArrayAdapter<Attribution> {

        creditListViewAdapter(@NonNull Context context, ArrayList<Attribution> resource) {
            super(context, 0, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Attribution item = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_credit, parent, false);
            }

            ((TextView) convertView.findViewById(R.id.creditTitle)).setText(item.getTitle());
            ((TextView) convertView.findViewById(R.id.creditName)).setText(item.getName());
            ((TextView) convertView.findViewById(R.id.linkText)).setText(item.getLinkText());

            return convertView;
        }
    }
}