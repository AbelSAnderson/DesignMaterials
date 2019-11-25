package com.example.designmaterials.fragments;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.designmaterials.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IconsFragment extends Fragment {


    public IconsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_icons, container, false);

        Typeface oswald,ubuntu;
        TextView txt1=view.findViewById(R.id.txt1);
        TextView txt2=view.findViewById(R.id.txt2);

        Typeface font=Typeface.createFromAsset(getActivity().getAssets(),"grotesquel.ttf");
//        ubuntu = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ubuntu-Regular.ttf");

        txt1.setTypeface(font);
//        txt2.setTypeface(ubuntu);

        return view;
    }

}
