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
import android.widget.Toast;

import com.example.designmaterials.R;

import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DesignFragment extends Fragment {

    private Button primaryColorDisplay;
    private Button lightColor;
    private Button darkColor;
    private Button primaryColorBtn;
    private Button secondaryColorBtn;

    private static int thePrimaryColor = Color.rgb(198, 40, 40);
    private static int thePrimaryColorLight = lighter(thePrimaryColor);
    private static int thePrimaryColorDark = darker(thePrimaryColor);
    private static int theSecondaryColor = Color.rgb(27, 94, 32);
    private static int theSecondaryColorLight = lighter(theSecondaryColor);
    private static int theSecondaryColorDark = darker(theSecondaryColor);
    private static String activeBtn = "primary";

    public DesignFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_design, container, false);

        primaryColorDisplay = view.findViewById(R.id.primaryColor);
        primaryColorDisplay.setTextColor(Color.WHITE);
        primaryColorDisplay.setBackgroundColor(thePrimaryColor);

        lightColor = view.findViewById(R.id.lightColor);
        lightColor.setTextColor(Color.WHITE);
        lightColor.setBackgroundColor(thePrimaryColorLight);

        primaryColorBtn = view.findViewById(R.id.primaryColorBtn);
        primaryColorBtn.setBackgroundColor(thePrimaryColor);
        primaryColorBtn.setTextColor(Color.WHITE);

        darkColor = view.findViewById(R.id.darkColor);
        darkColor.setTextColor(Color.WHITE);
        darkColor.setBackgroundColor(thePrimaryColorDark);

        secondaryColorBtn = view.findViewById(R.id.secondaryColorBtn);
        secondaryColorBtn.setBackgroundColor(theSecondaryColor);
        secondaryColorBtn.setTextColor(Color.WHITE);

        Button saveColorsBtn = view.findViewById(R.id.saveColorsBtn);

        ColorPickerView colorPickerView = view.findViewById(R.id.colorPickerView);
        colorPickerView.setPureColor(Color.RED);

        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {

                if (activeBtn.equals("primary")) {
                    //check if default color is white, if so, don't get it.
                    if (color != -65538) {
                        thePrimaryColor = color;
                    }

                    primaryColorDisplay.setBackgroundColor(thePrimaryColor);

                    primaryColorBtn.setBackgroundColor(thePrimaryColor);

                    thePrimaryColorDark = darker(thePrimaryColor);
                    darkColor.setBackgroundColor(thePrimaryColorDark);

                    thePrimaryColorLight = lighter(thePrimaryColor);
                    lightColor.setBackgroundColor(thePrimaryColorLight);

                } else {
                    if (color != -65538) {
                        theSecondaryColor = color;
                    }

                    primaryColorDisplay.setBackgroundColor(theSecondaryColor);
                    secondaryColorBtn.setBackgroundColor(theSecondaryColor);

                    theSecondaryColorDark = darker(theSecondaryColor);
                    darkColor.setBackgroundColor(theSecondaryColorDark);
                    theSecondaryColorLight = lighter(theSecondaryColor);
                    lightColor.setBackgroundColor(theSecondaryColorLight);
                }
            }
        });

        primaryColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primaryColorDisplay.setText(getString(R.string.designPrimaryColorDisplayPrimary));
                primaryColorDisplay.setBackgroundColor(thePrimaryColor);
                darkColor.setBackgroundColor(thePrimaryColorDark);
                lightColor.setBackgroundColor(thePrimaryColorLight);
                activeBtn = "primary";
            }
        });

        secondaryColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primaryColorDisplay.setText(getString(R.string.designPrimaryColorDisplaySecondary));
                primaryColorDisplay.setBackgroundColor(theSecondaryColor);
                darkColor.setBackgroundColor(theSecondaryColorDark);
                lightColor.setBackgroundColor(theSecondaryColorLight);
                activeBtn = "secondary";
            }
        });

        saveColorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("primaryColor", thePrimaryColor);
                editor.putInt("primaryColorDark", thePrimaryColorDark);
                editor.putInt("primaryColorLight", thePrimaryColorLight);
                editor.putInt("secondaryColor", theSecondaryColor);
                editor.putInt("secondaryColorDark", theSecondaryColorDark);
                editor.putInt("secondaryColorLight", theSecondaryColorLight);
                editor.apply();

                Toast toast = Toast.makeText(getContext(), getString(R.string.designColorSavedMessage), Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return view;
    }

    /**
     * Function to get the lighter version of a color
     *
     * @param color Color to be lightened
     * @return A Lighter version of the color
     */
    private static int lighter(int color) {
        int red = (int) ((Color.red(color) * (1 - (float) 0.2) / 255 + (float) 0.2) * 255);
        int green = (int) ((Color.green(color) * (1 - (float) 0.2) / 255 + (float) 0.2) * 255);
        int blue = (int) ((Color.blue(color) * (1 - (float) 0.2) / 255 + (float) 0.2) * 255);

        return Color.argb(Color.alpha(color), red, green, blue);
    }

    /**
     * Function to get the darker version of a color
     *
     * @param color Color to be darkened
     * @return A darker version of the color
     */
    private static int darker(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= (float) 0.8;
        return Color.HSVToColor(hsv);
    }
}