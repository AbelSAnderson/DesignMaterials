package com.example.designmaterials.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

    static Button primaryColorDisplayer;
    Button lightColor;
    Button darkColor;
    static Button primaryColorBtn;
    static Button secondaryColorBtn;

    static int thePrimaryColor=Color.rgb(198,40,40);
    static int thePrimaryColorLight=lighter(thePrimaryColor,0.2f);
    static int thePrimaryColorDark=darker(thePrimaryColor,0.8f);
    static int theSecondaryColor=Color.rgb(27,94,32);
    static int theSecondaryColorLight=lighter(theSecondaryColor,0.2f);
    static int theSecondaryColorDark=darker(theSecondaryColor,0.8f);
    static String activeBtn="primary";
    Button saveColorsBtn;

    public DesignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_design, container, false);

        primaryColorDisplayer=view.findViewById(R.id.primaryColor);
        primaryColorDisplayer.setTextColor(Color.WHITE);
        primaryColorDisplayer.setBackgroundColor(thePrimaryColor);
        lightColor=view.findViewById(R.id.lightColor);
        lightColor.setTextColor(Color.WHITE);
        lightColor.setBackgroundColor(thePrimaryColorLight);
        primaryColorBtn=view.findViewById(R.id.primaryColorBtn);
        primaryColorBtn.setBackgroundColor(thePrimaryColor);
        primaryColorBtn.setTextColor(Color.WHITE);
        darkColor=view.findViewById(R.id.darkColor);
        darkColor.setTextColor(Color.WHITE);
        darkColor.setBackgroundColor(thePrimaryColorDark);
        secondaryColorBtn=view.findViewById(R.id.secondaryColorBtn);
        secondaryColorBtn.setBackgroundColor(theSecondaryColor);
        secondaryColorBtn.setTextColor(Color.WHITE);
        saveColorsBtn=view.findViewById(R.id.saveColorsBtn);

        final ColorPickerView colorPickerView = view.findViewById(R.id.colorPickerView);
        colorPickerView.setPureColor(Color.BLUE);


        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {
                colorPickerView.setPureColor(Color.BLUE);

                if(activeBtn=="primary"){
                    //check if default color is white, if so, don't get it.
                    if(color!=-65538){
                        thePrimaryColor=color;
                    }

                    primaryColorDisplayer.setBackgroundColor(thePrimaryColor);
                    primaryColorDisplayer.setText("Primary");

                    primaryColorBtn.setBackgroundColor(thePrimaryColor);

                    thePrimaryColorDark=darker(thePrimaryColor,0.8f);
                    darkColor.setBackgroundColor(thePrimaryColorDark);

                    thePrimaryColorLight=lighter(thePrimaryColor,0.2f);
                    lightColor.setBackgroundColor(thePrimaryColorLight);

                }else{
                    theSecondaryColor=color;

                    primaryColorDisplayer.setText("Secondary");
                    primaryColorDisplayer.setBackgroundColor(theSecondaryColor);
                    secondaryColorBtn.setBackgroundColor(theSecondaryColor);

                    theSecondaryColorDark=darker(theSecondaryColor,0.8f);
                    darkColor.setBackgroundColor(theSecondaryColorDark);
                    theSecondaryColorLight=lighter(theSecondaryColor,0.2f);
                    lightColor.setBackgroundColor(theSecondaryColorLight);
                }
            }
        });
        primaryColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primaryColorDisplayer.setText("Primary");
                primaryColorDisplayer.setBackgroundColor(thePrimaryColor);
                darkColor.setBackgroundColor(thePrimaryColorDark);
                lightColor.setBackgroundColor(thePrimaryColorLight);
                activeBtn="primary";
            }
        });
        secondaryColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primaryColorDisplayer.setText("Secondary");
                primaryColorDisplayer.setBackgroundColor(theSecondaryColor);
                darkColor.setBackgroundColor(theSecondaryColorDark);
                lightColor.setBackgroundColor(theSecondaryColorLight);
                activeBtn="secondary";
            }
        });

        saveColorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("primaryColor",thePrimaryColor);
                editor.putInt("primaryColorDark",thePrimaryColorDark);
                editor.putInt("primaryColorLight",thePrimaryColorLight);
                editor.putInt("secondaryColor",theSecondaryColor);
                editor.putInt("secondaryColorDark",theSecondaryColorDark);
                editor.putInt("secondaryColorLight",theSecondaryColorLight);
                editor.commit();

                Toast toast=Toast.makeText(getContext(),"Color Saved. Check the home Page",Toast.LENGTH_LONG);
                toast.show();

                Navigation.findNavController(view).navigate(R.id.action_nav_design_to_nav_elements);
            }
        });

        return view;
    }


    /**
     * Function to get the lighter version of a color
     * @param color
     * @param factor
     * @return
     */
    public static int lighter(int color, float factor){
        int red=(int) ((Color.red(color)*(1-factor)/255+factor)*255);
        int green=(int) ((Color.green(color)*(1-factor)/255+factor)*255);
        int blue=(int) ((Color.blue(color)*(1-factor)/255+factor)*255);

        return Color.argb(Color.alpha(color),red,green,blue);
    }

    /**
     * Function to get the darker version of a color
     * @param color
     * @param factor
     * @return
     */
    public static int darker(int color, float factor){
        float[] hsv=new float[3];
        Color.colorToHSV(color,hsv);
        hsv[2] *= factor;
        return Color.HSVToColor(hsv);
    }

}
