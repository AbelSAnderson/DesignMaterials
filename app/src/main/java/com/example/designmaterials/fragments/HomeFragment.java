package com.example.designmaterials.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.designmaterials.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the lonCreateayout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button primaryColor = view.findViewById(R.id.primaryColor);
        Button primaryColorLight = view.findViewById(R.id.primaryColorLight);
        Button primaryColorDark = view.findViewById(R.id.primaryColorDark);

        Button secondaryColor = view.findViewById(R.id.secondaryColor);
        Button secondaryColorLight = view.findViewById(R.id.secondaryColorLight);
        Button secondaryColorDark = view.findViewById(R.id.secondaryColorDark);

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int thePrimaryColor = sharedPreferences.getInt("primaryColor", Color.RED);
        int thePrimaryColorDark = sharedPreferences.getInt("primaryColorDark", Color.RED);
        int thePrimaryColorLight = sharedPreferences.getInt("primaryColorLight", Color.RED);


        int theSecondaryColor=sharedPreferences.getInt("secondaryColor",Color.GREEN);
        int theSecondaryColorDark=sharedPreferences.getInt("secondaryColorDark",Color.GREEN);
        int theSecondaryColorLight=sharedPreferences.getInt("secondaryColorLight",Color.GREEN);
        String headingFontName=sharedPreferences.getString("headingFontName","opensans");
        String bodyFontName=sharedPreferences.getString("bodyFontName","opensans");
        String buttonFontName=sharedPreferences.getString("buttonFontName","opensans");
        String headingFontWeight=sharedPreferences.getString("headingFontWeight","");
        String bodyFontWeight=sharedPreferences.getString("bodyFontWeight","");
        String buttonFontWeight=sharedPreferences.getString("buttonFontWeight","");
        int headingsFontsize=sharedPreferences.getInt("headingFontSize",22);
        int bodyFontsize=sharedPreferences.getInt("bodyFontSize",22);
        int buttonFontSize=sharedPreferences.getInt("buttonFontSize",22);
//        System.out.println("thePrimaryColor:"+thePrimaryColor);
//        System.out.println("thePrimaryColorLight:"+thePrimaryColorLight);
//        System.out.println("thePrimaryColorDark:"+thePrimaryColorDark);
//        System.out.println("theSecondaryColor:"+theSecondaryColor);
//        System.out.println("theSecondaryColorLight:"+theSecondaryColorLight);
//        System.out.println("theSecondaryColorDark:"+theSecondaryColorDark);
//        System.out.println("headingFontName:"+headingFontName);
//        System.out.println("bodyFontName:"+bodyFontName);
//        System.out.println("buttonFontName:"+buttonFontName);
//        System.out.println("headingFontWeight:"+headingFontWeight);
//        System.out.println("bodyFontWeight:"+bodyFontWeight);
//        System.out.println("buttonFontWeight:"+buttonFontWeight);
//        System.out.println("headingsFontsize:"+headingsFontsize);
//        System.out.println("bodyFontsize:"+bodyFontsize);
//        System.out.println("buttonFontSize:"+buttonFontSize);





        primaryColor.setBackgroundColor(thePrimaryColor);
        primaryColor.setTypeface(ElementFragment.elementFont(getActivity(), headingFontName, headingFontWeight));
        primaryColor.setTextSize(headingsFontsize);
        primaryColorDark.setBackgroundColor(thePrimaryColorDark);
        primaryColorDark.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
        primaryColorDark.setTextSize(bodyFontsize);
        primaryColorLight.setBackgroundColor(thePrimaryColorLight);
        primaryColorLight.setTypeface(ElementFragment.elementFont(getActivity(), buttonFontName, buttonFontWeight));
        primaryColorLight.setTextSize(buttonFontSize);

        secondaryColor.setBackgroundColor(theSecondaryColor);
        secondaryColorDark.setBackgroundColor(theSecondaryColorDark);
        secondaryColorLight.setBackgroundColor(theSecondaryColorLight);

        return view;
    }
}