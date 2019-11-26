package com.example.designmaterials.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import com.example.designmaterials.R;
import com.example.designmaterials.javabeans.Element;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementFragment extends Fragment {

    private static String activeFontName = "opensans";
    private static String activeFontWeight = "";

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

        View vv = elemnt.getViewElement();

        TextView temp = new TextView(getActivity());
        DiscreteSeekBar sBar = view.findViewById(R.id.fontSize);
        linearLayout.removeAllViews();
        Typeface font = setElementFont(getActivity(), activeFontName, activeFontWeight);

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
            txt.setText(getString(R.string.elementMultiAutoCompleteTextView));
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            txt.setTypeface(font);
            temp = txt;
//            linearLayout.addView(txt);
        } else if (vv instanceof AutoCompleteTextView) {
            AutoCompleteTextView txt = new AutoCompleteTextView(getActivity());
            txt.setText(getString(R.string.elementAutoCompleteTextView));
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            temp = txt;
            txt.setTypeface(font);
//            linearLayout.addView(txt);
        } else if (vv instanceof EditText) {
            EditText txt = new EditText(getActivity());
            txt.setText(getString(R.string.elementEditText));
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            txt.setTypeface(font);
            temp = txt;
//            linearLayout.addView(txt);
        } else if (vv instanceof TextView) {
            TextView txt = new TextView(getActivity());
            if (vv.getTag().toString().equals("title")) {
                txt.setText(R.string.text_heading);
            } else {
                txt.setText(R.string.text_long);
            }

            txt.setTextSize(22);
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
                            activeFontWeight = "";
                            break;
                        case "light":
                            activeFontWeight = "l";
                            break;
                        case "bold":
                            activeFontWeight = "b";
                            break;
                    }
                    finalTemp.setTypeface(setElementFont(getActivity(), activeFontName, activeFontWeight));
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
                    finalTemp1.setTypeface(setElementFont(getActivity(), activeFontName, activeFontWeight));
                }
            });
        }

        linearLayout.addView(temp);

//        linearLayout.addView(vv);

        return view;
    }

    /**
     * @param activity   activity where the element is
     * @param fontName   choose a name that already exists on the folder
     * @param fontWeight ""=regular  "l"=light "b"=bold
     * @return fontType
     */
    public static Typeface setElementFont(Activity activity, String fontName, String fontWeight) {
        Typeface font = Typeface.createFromAsset(activity.getAssets(), fontName + fontWeight + ".ttf");
        return font;
    }
}