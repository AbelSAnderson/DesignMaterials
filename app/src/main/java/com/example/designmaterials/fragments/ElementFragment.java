package com.example.designmaterials.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designmaterials.MainActivity;
import com.example.designmaterials.R;
import com.example.designmaterials.javabeans.Element;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementFragment extends Fragment {


    public static String headingFontName = "grotesque";
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
    public static String activeElement="";
    public static int editedElements=0;

    static int[] btnIds = new int[]{R.id.roboto, R.id.opensans, R.id.grotesque, R.id.montserrat, R.id.playfairdisplay};
    static final Button[] arrayButtons = new Button[btnIds.length];


    public ElementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int sharedHeadingsFontsize=sharedPreferences.getInt("headingFontSize",22);
        int sharedBodyFontsize=sharedPreferences.getInt("bodyFontSize",22);
        int sharedButtonFontSize=sharedPreferences.getInt("buttonFontSize",22);
        String sharedHeadingFontWeight=sharedPreferences.getString("headingFontWeight","");
        String sharedBodyFontWeight=sharedPreferences.getString("bodyFontWeight","");
        String sharedButtonFontWeight=sharedPreferences.getString("buttonFontWeight","");

        final View view = inflater.inflate(R.layout.fragment_element, container, false);
        final int thePrimaryColor = sharedPreferences.getInt("primaryColor", 123);
        final int thePrimaryColorDark = sharedPreferences.getInt("primaryColorDark", 123);


        ScrollView linearLayout = view.findViewById(R.id.elementHolder);



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
            btn.setText(R.string.button_text);
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundColor(thePrimaryColor);
            btn.setTypeface(font);
            btn.setTextSize(sharedButtonFontSize);
            temp = btn;
            sBar.setProgress(sharedButtonFontSize);
            SetActiveFontWeight(sharedButtonFontWeight,view);
            activeElement="button";

        }  else if (vv instanceof TextView) {
            TextView txt = new TextView(getActivity());
            if (vv.getTag().toString().equals("title")) {
                sBar.setProgress(sharedHeadingsFontsize);
                txt.setText(R.string.text_heading);
                txt.setTextSize(sharedHeadingsFontsize);
                SetActiveFontWeight(sharedHeadingFontWeight,view);
                activeElement="title";
            } else {
                sBar.setProgress(sharedBodyFontsize);
                txt.setText(R.string.text_long);
                txt.setTextSize(sharedBodyFontsize);
                SetActiveFontWeight(sharedBodyFontWeight,view);
                activeElement="body";
            }

            txt.setTextColor(thePrimaryColor);
            temp = txt;
            txt.setTypeface(font);
        }

        final TextView finalTemp = temp;
        sBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                finalTemp.setTextSize(value);
                if(activeElement=="button"){
                    buttonFontsize=value;
                }else if(activeElement=="title"){
                    headingsFontsize=value;
                }else if(activeElement=="body"){
                    bodyFontsize=value;
                }

            }
//        (TextView)temp.setTypeface(font);\

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {}
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
                            if(activeElement=="button"){
                                buttonFontWeight="";
                            }else if(activeElement=="title"){
                                headingFontWeight="";
                            }else if(activeElement=="body"){
                                bodyFontWeight="";
                            }
                            activeFontWeight="";
                            break;
                        case "light":
                            if(activeElement=="button"){
                                buttonFontWeight="l";
                            }else if(activeElement=="title"){
                                headingFontWeight="l";
                            }else if(activeElement=="body"){
                                bodyFontWeight="l";
                            }
                            activeFontWeight="l";
                            break;
                        case "bold":
                            if(activeElement=="button"){
                                buttonFontWeight="b";
                            }else if(activeElement=="title"){
                                headingFontWeight="b";
                            }else if(activeElement=="body"){
                                bodyFontWeight="b";
                            }
                            activeFontWeight="b";
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
                    resetButtonsStyle(thePrimaryColor);
                    activeFontName = arrayButtons[finalI].getTag().toString();
                    finalTemp1.setTypeface(elementFont(getActivity(), activeFontName, activeFontWeight));
                    if(activeElement=="button"){
                        buttonFontName=arrayButtons[finalI].getTag().toString();
                    }else if(activeElement=="title"){
                        headingFontName=arrayButtons[finalI].getTag().toString();
                    }else if(activeElement=="body"){
                        bodyFontName=arrayButtons[finalI].getTag().toString();
                    }
                    arrayButtons[finalI].setBackgroundColor(thePrimaryColorDark);
                }
            });

            if(activeElement=="button"){
                if(arrayButtons[i].getTag().equals(buttonFontName)){
                    arrayButtons[i].setBackgroundColor(thePrimaryColorDark);
                }
            }else if(activeElement=="title"){
                if(arrayButtons[i].getTag().equals(headingFontName)){
                    arrayButtons[i].setBackgroundColor(thePrimaryColorDark);
                }
            }else if(activeElement=="body"){
                if(arrayButtons[i].getTag().equals(bodyFontName)){
                    arrayButtons[i].setBackgroundColor(thePrimaryColorDark);
                }
            }
        }

        linearLayout.addView(temp);

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
                editedElements++;
                Toast.makeText(getContext(), "Style Saved", Toast.LENGTH_SHORT).show();

                if(editedElements>2){
                        Navigation.findNavController(view).navigate(R.id.action_elementFragment_to_nav_views);
                    }


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

    /**
     * Function to grab the active FontWeight from sharedPreferences and mark checked that radio button
     * @param weight
     * @param view
     */
    public static void SetActiveFontWeight(String weight, View view){
        switch (weight){
            case "":
                RadioButton reg=view.findViewById(R.id.regular);
                reg.setChecked(true);
                break;
            case "b":
                RadioButton bold=view.findViewById(R.id.bold);
                bold.setChecked(true);
                break;
            case "l":
                RadioButton light=view.findViewById(R.id.light);
                light.setChecked(true);
                break;
        }
    }

    /**
     * Function to get the active font from SharedPreferences and mark that button as active.
     * @param fontName
     * @param view
     */
    public static void SetActiveFontFamily(String fontName, View view){
        switch (fontName){
            case "":
                RadioButton reg=view.findViewById(R.id.regular);
                reg.setChecked(true);
                break;
            case "b":
                RadioButton bold=view.findViewById(R.id.bold);
                bold.setChecked(true);
                break;
            case "l":
                RadioButton light=view.findViewById(R.id.light);
                light.setChecked(true);
                break;
        }
    }

    public static void resetButtonsStyle(int color){
        for (int i = 0; i < arrayButtons.length; i++) {
            arrayButtons[i].setBackgroundColor(color);
        }
    }
}