package com.example.designmaterials.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designmaterials.R;
import com.example.designmaterials.javabeans.Element;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ElementFragment extends Fragment {

    public static String headingFontName = "opensans";
    public static String bodyFontName = "opensans";
    public static String buttonFontName = "roboto";
    public static String activeFontName = "roboto";
    public static String headingFontWeight = "";
    public static String bodyFontWeight = "";
    public static String buttonFontWeight = "";
    public static String activeFontWeight = "";
    public static int headingsFontsize=32;
    public static int bodyFontsize=22;
    public static int buttonFontsize=20;

    public ElementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_element, container, false);
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int thePrimaryColor = sharedPreferences.getInt("primaryColor", 123);
        ScrollView linearLayout = view.findViewById(R.id.elementHolder);

        int[] btnIds = new int[]{R.id.roboto, R.id.opensans, R.id.grotesque, R.id.montserrat, R.id.playfairdisplay};
        final Button[] arrayButtons = new Button[btnIds.length];

        RadioGroup fontWeightGroup = view.findViewById(R.id.fontWeightGroup);

        Bundle bundle = this.getArguments();

        Element elemnt = (Element) bundle.getSerializable("Element");

        final View vv = elemnt.getViewElement();

        TextView temp = new TextView(getActivity());
        DiscreteSeekBar sBar = view.findViewById(R.id.fontSize);
        linearLayout.removeAllViews();
        Typeface font = elementFont(getActivity(), bodyFontName, bodyFontWeight);

        if (vv instanceof Button) {
            Button btn = new Button(getActivity());
//            btn.setText("Click me nice");
            btn.setText(vv.getTag().toString());
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundColor(thePrimaryColor);
            btn.setTypeface(font);
            temp = btn;
//            linearLayout.addView(btn);
        } else if (vv instanceof MultiAutoCompleteTextView) {
            MultiAutoCompleteTextView txt = new MultiAutoCompleteTextView(getActivity());
            txt.setText("The MultiAutoCompleteTextView Lorem ipsum has a lot of characters");
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            txt.setTypeface(font);
            temp = txt;
//            linearLayout.addView(txt);
        } else if (vv instanceof AutoCompleteTextView) {
            AutoCompleteTextView txt = new AutoCompleteTextView(getActivity());
            txt.setText("The AutoCompleteTextView Lorem ipsum has a lot of characters");
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            temp = txt;
            txt.setTypeface(font);
//            linearLayout.addView(txt);
        } else if (vv instanceof EditText) {
            EditText txt = new EditText(getActivity());
            txt.setText("The Edit text Lorem ipsum has a lot of characters");
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            txt.setTypeface(font);
            temp = txt;
//            linearLayout.addView(txt);
        } else if (vv instanceof TextView) {
            TextView txt = new TextView(getActivity());
            if(vv.getTag().toString()=="title"){
                txt.setText(R.string.text_heading);
            }else{
                txt.setText(R.string.text_long);
            }

            txt.setTextSize(bodyFontsize);
            txt.setTextColor(thePrimaryColor);
            temp = txt;
            txt.setTypeface(font);
//            linearLayout.addView(txt);
//            Log.wtf("wtf","wtf again");
        }
//        (TextView)temp.setTypeface(font);\

        final TextView finalTemp = temp;
        sBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                finalTemp.setTextSize(value);
                if(vv instanceof Button){
                    buttonFontsize=value;
                }else if(vv instanceof TextView){
                    if(vv.getTag().toString()=="title"){
                        headingsFontsize=value;
                    }else{
                        bodyFontsize=value;
                    }
                }

            }
            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        fontWeightGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //get the id of selected radio and update activeFontWeight
                int selectedWeight = radioGroup.getCheckedRadioButtonId();
                if (selectedWeight != -1) {
                    RadioButton radioButton = view.findViewById(selectedWeight);
                    switch (radioButton.getTag().toString()) {
                        case "regular":
                            if(vv instanceof Button){
                                buttonFontWeight="";
                            }else if(vv instanceof TextView){
                                if(vv.getTag().toString()=="title"){
                                    headingFontWeight="";
                                }else{
                                    bodyFontWeight="";
                                }
                            }
                            break;
                        case "light":
                            if(vv instanceof Button){
                                buttonFontWeight="l";
                            }else if(vv instanceof TextView){
                                if(vv.getTag().toString()=="title"){
                                    headingFontWeight="l";
                                }else{
                                    bodyFontWeight="l";
                                }
                            }
                            break;
                        case "bold":
                            if(vv instanceof Button){
                                buttonFontWeight="b";
                            }else if(vv instanceof TextView){
                                if(vv.getTag().toString()=="title"){
                                    headingFontWeight="b";
                                }else{
                                    bodyFontWeight="b";
                                }
                            }
                            break;
                    }
                    finalTemp.setTypeface(elementFont(getActivity(), activeFontName, activeFontWeight));
                }
            }
        });
        final TextView finalTemp1 = temp;

        for (int i = 0; i < arrayButtons.length; i++) {
            arrayButtons[i] = view.findViewById(btnIds[i]);
            arrayButtons[i].setBackgroundColor(thePrimaryColor);
            arrayButtons[i].setTextColor(Color.WHITE);
            final int finalI = i;
            arrayButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activeFontName = arrayButtons[finalI].getTag().toString();
                    finalTemp1.setTypeface(elementFont(getActivity(), activeFontName, activeFontWeight));
                }
            });
        }

        linearLayout.addView(temp);


//        linearLayout.addView(vv);

        Button saveFont=view.findViewById(R.id.saveFont);
        saveFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("headingFontName",headingFontName);
                editor.putString("bodyFontName",bodyFontName);
                editor.putString("buttonFontName",buttonFontName);
                editor.putString("headingFontWeight",headingFontWeight);
                editor.putString("bodyFontWeight",bodyFontWeight);
                editor.putString("buttonFontWeight",buttonFontWeight);
                editor.putInt("headingFontSize",headingsFontsize);
                editor.putInt("bodyFontSize",bodyFontsize);
                editor.putInt("buttonFontSize",buttonFontsize);
                editor.commit();

                Toast toast=Toast.makeText(getContext(),"Fonts Saved. Check the home Page",Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return view;
    }

    /**
     * @param activity   activity where the element is
     * @param fontName   choose a name that already exists on the folder
     * @param fontWeight ""=regular  "l"=light "b"=bold
     * @return fontType
     */
    public static Typeface elementFont(Activity activity, String fontName, String fontWeight) {
        Typeface font = Typeface.createFromAsset(activity.getAssets(), fontName + fontWeight + ".ttf");
        return font;
    }

}
