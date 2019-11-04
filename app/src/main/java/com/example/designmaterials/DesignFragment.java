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

import yuku.ambilwarna.AmbilWarnaDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class DesignFragment extends Fragment {

    LinearLayout colorContainer;
    int mDefaultColor;
    Button colorPickBtn;
    TextView lightColor;

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
        colorPickBtn=view.findViewById(R.id.colorPickBtn);
        lightColor=view.findViewById(R.id.colValue);

        colorPickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });

        return view;
    }

    public void openColorPicker() {
        AmbilWarnaDialog colorPicker=new AmbilWarnaDialog(getContext(), mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                int alpha = Color.alpha(color);
                mDefaultColor=color;
                colorContainer.setBackgroundColor(mDefaultColor);
                String hexColor = String.format("#%06X", (0xFFFFFF & mDefaultColor));
//                lightColor.setText(hexColor);
                int manipulated=manipulateColor(color,30);
                lightColor.setText(manipulated+"");
//                lightColor.setText(" red:"+red+"\n green:"+green+"\n blue:"+blue+"\n alpha:"+alpha);
            }
        });
        colorPicker.show();
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

}
