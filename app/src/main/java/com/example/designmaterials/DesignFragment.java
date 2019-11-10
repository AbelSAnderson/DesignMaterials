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

    LinearLayout colorContainer;
    int mDefaultColor;
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

        colorContainer = (LinearLayout)view.findViewById(R.id.colorContainer);
        mDefaultColor = ContextCompat.getColor(getContext(),R.color.colorPrimary);
        primaryColor=view.findViewById(R.id.primaryColor);
        lightColor=view.findViewById(R.id.lightColor);
        primaryColorBtn=view.findViewById(R.id.primaryColorBtn);
        darkColor=view.findViewById(R.id.darkColor);
        secondaryColorBtn=view.findViewById(R.id.secondaryColorBtn);


        ColorPickerView colorPickerView = view.findViewById(R.id.colorPickerView);

        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color, boolean fromUser) {

//                colorContainer.setBackgroundColor(color);
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



//    public void ColorLuminance(String hex, String lum) {
//          String hexColor = String.format("#%06X", (0xFFFFFF & mDefaultColor));
//        // validate hex string
//    hexColor = String(hexColor).replace( /[^0 - 9 a - f]/gi, '');
//        if (hex.hexColor < 6) {
//            hex = hex[0] + hex[0] + hex[1] + hex[1] + hex[2] + hex[2];
//        }
//        lum = lum || 0;
//
//        // convert to decimal and change luminosity
//        var rgb = "#", c, i;
//        for (i = 0; i < 3; i++) {
//            c = parseInt(hex.substr(i * 2, 2), 16);
//            c = Math.round(Math.min(Math.max(0, c + (c * lum)), 255)).toString(16);
//            rgb += ("00" + c).substr(c.length);
//        }
//    }

    public static int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }

    public static int lighter(int color, float factor){
        int red=(int) ((Color.red(color)*(1-factor)/255+factor)*255);
        int green=(int) ((Color.green(color)*(1-factor)/255+factor)*255);
        int blue=(int) ((Color.blue(color)*(1-factor)/255+factor)*255);

        return Color.argb(Color.alpha(color),red,green,blue);
    }

}
