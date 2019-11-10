package com.example.designmaterials;


import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DesignFragment extends Fragment {

    Button primaryColor;
    Button lightColor;
    Button darkColor;
    Button primaryColorBtn;
    Button secondaryColorBtn;

    public DesignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_design, container, false);

        primaryColor=view.findViewById(R.id.primaryColor);
        lightColor=view.findViewById(R.id.lightColor);
        primaryColorBtn=view.findViewById(R.id.primaryColorBtn);
        darkColor=view.findViewById(R.id.darkColor);
        secondaryColorBtn=view.findViewById(R.id.secondaryColorBtn);


        ColorPickerView colorPickerView = view.findViewById(R.id.colorPickerView);

        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {

                primaryColor.setBackgroundColor(color);
                primaryColor.setTextColor(Color.WHITE);

                float[] hsv=new float[3];
                Color.colorToHSV(color,hsv);
                hsv[2] *= 0.8f;

                int tempDarkColor=Color.HSVToColor(hsv);
                darkColor.setBackgroundColor(tempDarkColor);
                darkColor.setTextColor(Color.WHITE);

                lightColor.setBackgroundColor(lighter(color,0.2f));
                lightColor.setTextColor(Color.WHITE);
            }
        });
        primaryColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primaryColor.setText("Primary");
            }
        });
        secondaryColorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primaryColor.setText("Secondary");
            }
        });

        return view;
    }





    public static int lighter(int color, float factor){
        int red=(int) ((Color.red(color)*(1-factor)/255+factor)*255);
        int green=(int) ((Color.green(color)*(1-factor)/255+factor)*255);
        int blue=(int) ((Color.blue(color)*(1-factor)/255+factor)*255);

        return Color.argb(Color.alpha(color),red,green,blue);
    }

}
