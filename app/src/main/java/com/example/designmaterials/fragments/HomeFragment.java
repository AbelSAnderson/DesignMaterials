package com.example.designmaterials.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.designmaterials.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    Button primaryColor;
    Button primaryColorLight;
    Button primaryColorDark;
    Button secondaryColor;
    Button secondaryColorLight;
    Button secondaryColorDark;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        primaryColor=view.findViewById(R.id.primaryColor);
        primaryColorLight=view.findViewById(R.id.primaryColorLight);
        primaryColorDark=view.findViewById(R.id.primaryColorDark);

        secondaryColor=view.findViewById(R.id.secondaryColor);
        secondaryColorLight=view.findViewById(R.id.secondaryColorLight);
        secondaryColorDark=view.findViewById(R.id.secondaryColorDark);

        SharedPreferences sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        int thePrimaryColor=sharedPreferences.getInt("primaryColor",123);
        int thePrimaryColorDark=sharedPreferences.getInt("primaryColorDark",123);
        int thePrimaryColorLight=sharedPreferences.getInt("primaryColorLight",123);

        int theSecondaryColor=sharedPreferences.getInt("secondaryColor",123);
        int theSecondaryColorDark=sharedPreferences.getInt("secondaryColorDark",123);
        int theSecondaryColorLight=sharedPreferences.getInt("secondaryColorLight",123);

        primaryColor.setBackgroundColor(thePrimaryColor);
        primaryColorDark.setBackgroundColor(thePrimaryColorDark);
        primaryColorLight.setBackgroundColor(thePrimaryColorLight);

        secondaryColor.setBackgroundColor(theSecondaryColor);
        secondaryColorDark.setBackgroundColor(theSecondaryColorDark);
        secondaryColorLight.setBackgroundColor(theSecondaryColorLight);

        return view;
    }

}
