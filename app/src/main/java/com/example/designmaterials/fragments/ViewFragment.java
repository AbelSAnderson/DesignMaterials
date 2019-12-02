package com.example.designmaterials.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.designmaterials.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment {
    private static final String LAYOUT = "layout";

    private int layout;




    public ViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param layout Layout to be displayed
     * @return A new instance of fragment ViewFragment.
     */
    public static ViewFragment newInstance(int layout) {
        ViewFragment fragment = new ViewFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT, layout);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            layout = getArguments().getInt(LAYOUT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get values from shared preferences
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int thePrimaryColor = sharedPreferences.getInt("primaryColor", 123);
        int thePrimaryColorDark = sharedPreferences.getInt("primaryColorDark", 123);
        int thePrimaryColorLight = sharedPreferences.getInt("primaryColorLight", 123);


        int theSecondaryColor=sharedPreferences.getInt("secondaryColor",123);
        int theSecondaryColorDark=sharedPreferences.getInt("secondaryColorDark",123);
        int theSecondaryColorLight=sharedPreferences.getInt("secondaryColorLight",123);
        String headingFontName=sharedPreferences.getString("headingFontName","opensans");
        String bodyFontName=sharedPreferences.getString("bodyFontName","opensans");
        String buttonFontName=sharedPreferences.getString("buttonFontName","opensans");
        String headingFontWeight=sharedPreferences.getString("headingFontWeight","");
        String bodyFontWeight=sharedPreferences.getString("bodyFontWeight","");
        String buttonFontWeight=sharedPreferences.getString("buttonFontWeight","");
        int headingsFontsize=sharedPreferences.getInt("headingFontSize",22);
        int bodyFontsize=sharedPreferences.getInt("bodyFontSize",22);
        int buttonFontSize=sharedPreferences.getInt("buttonFontSize",22);

        Typeface fa=Typeface.createFromAsset(getActivity().getAssets(),"fontawesome.ttf");
        // Inflate the layout for this fragment
        View view = inflater.inflate(layout, container, false);
        if(layout ==R.layout.layout_view_one){
            TextView title=view.findViewById(R.id.title);
            TextView email=view.findViewById(R.id.emailLabel);
            TextView password=view.findViewById(R.id.passwordLabel);
            Button submit=view.findViewById(R.id.submit);
            title.setTextColor(thePrimaryColor);
            title.setTypeface(ElementFragment.elementFont(getActivity(), headingFontName, headingFontWeight));
            title.setTextSize(headingsFontsize);
            email.setTextColor(theSecondaryColor);
            email.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            email.setTextSize(bodyFontsize);
            password.setTextColor(theSecondaryColor);
            password.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            password.setTextSize(bodyFontsize);
            submit.setTextColor(Color.WHITE);
            submit.setBackgroundColor(thePrimaryColor);
            submit.setTypeface(ElementFragment.elementFont(getActivity(), buttonFontName, buttonFontWeight));
            submit.setTextSize(buttonFontSize);

        }else if(layout ==R.layout.layout_view_two){
            TextView prdTitle=view.findViewById(R.id.prdTitle);
            TextView amountText=view.findViewById(R.id.amountText);
            Switch longSleeveSwitch=view.findViewById(R.id.longSleeveSwitch);
            Button addButton=view.findViewById(R.id.addButton);

            Chip smallChip=view.findViewById(R.id.smallChip);
            Chip mediumChip=view.findViewById(R.id.mediumChip);
            Chip largeChip=view.findViewById(R.id.largeChip);

            smallChip.setTextColor(Color.WHITE);
            smallChip.setChipBackgroundColor(ColorStateList.valueOf(thePrimaryColorLight));
            mediumChip.setChipBackgroundColor(ColorStateList.valueOf(thePrimaryColor));
            largeChip.setChipBackgroundColor(ColorStateList.valueOf(thePrimaryColorDark));
            smallChip.setTextSize(bodyFontsize-5);

            mediumChip.setTextColor(Color.WHITE);
            mediumChip.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            mediumChip.setTextSize(bodyFontsize-2);

            largeChip.setTextColor(Color.WHITE);
            largeChip.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            largeChip.setTextSize(bodyFontsize+1);

            prdTitle.setTextColor(thePrimaryColor);
            prdTitle.setTypeface(ElementFragment.elementFont(getActivity(), headingFontName, headingFontWeight));
            prdTitle.setTextSize(headingsFontsize);

            amountText.setTextColor(theSecondaryColorDark);
            amountText.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            amountText.setTextSize(bodyFontsize);

            longSleeveSwitch.setTextColor(theSecondaryColorDark);
            longSleeveSwitch.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            longSleeveSwitch.setTextSize(bodyFontsize);

            addButton.setTextColor(Color.WHITE);
            addButton.setBackgroundColor(thePrimaryColor);
            addButton.setTypeface(ElementFragment.elementFont(getActivity(), buttonFontName, buttonFontWeight));
            addButton.setTextSize(buttonFontSize);


        }else if(layout ==R.layout.layout_view_three){
            TextView blogTitle=view.findViewById(R.id.blogTitle);
            TextView blogBody=view.findViewById(R.id.blogBody);
            Button btn1=view.findViewById(R.id.btn1);


            blogTitle.setTextColor(thePrimaryColor);
            blogTitle.setTypeface(ElementFragment.elementFont(getActivity(), headingFontName, headingFontWeight));
            blogTitle.setTextSize(headingsFontsize);

            blogBody.setTextColor(theSecondaryColor);
            blogBody.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            blogBody.setTextSize(bodyFontsize);

            btn1.setTextColor(Color.WHITE);
            btn1.setBackgroundColor(thePrimaryColorDark);
            btn1.setTypeface(ElementFragment.elementFont(getActivity(), buttonFontName, buttonFontWeight));
            btn1.setTextSize(buttonFontSize);
        }else if(layout ==R.layout.layout_view_four){
            int[] loveIds = new int[]{R.id.heart1, R.id.heart3};
            int[] saveIds = new int[]{R.id.save1, R.id.save3};
            int[] shareIds = new int[]{R.id.share1, R.id.share3};
            final TextView[] arraylove = new TextView[loveIds.length];
            final TextView[] arraysave = new TextView[saveIds.length];
            final TextView[] arrayshare = new TextView[shareIds.length];
            String heart="&#xf004";
            String valHexStrlove=heart.replace("&#x","").replace(";","");
            long valLongHeart=Long.parseLong(valHexStrlove,16);
            String bookmark="&#xf02e";
            String valHexStrbookmark=bookmark.replace("&#x","").replace(";","");
            long valLongBookmart=Long.parseLong(valHexStrbookmark,16);
            String share="&#xf14d";
            String valHexStrshare=share.replace("&#x","").replace(";","");
            long valLongShare=Long.parseLong(valHexStrshare,16);
            for (int i = 0; i < arraylove.length; i++) {
                arraylove[i] = view.findViewById(loveIds[i]);
                arraylove[i].setText((char)valLongHeart+"");
                arraylove[i].setTypeface(fa);
                arraylove[i].setTextColor(thePrimaryColor);
                arraylove[i].setTextSize(headingsFontsize);
            }
            for (int i = 0; i < arraysave.length; i++) {
                arraysave[i] = view.findViewById(saveIds[i]);
                arraysave[i].setText((char)valLongBookmart+"");
                arraysave[i].setTypeface(fa);
                arraysave[i].setTextColor(thePrimaryColor);
                arraysave[i].setTextSize(headingsFontsize);
            }
            for (int i = 0; i < arrayshare.length; i++) {
                arrayshare[i] = view.findViewById(shareIds[i]);
                arrayshare[i].setText((char)valLongShare+"");
                arrayshare[i].setTypeface(fa);
                arrayshare[i].setTextColor(thePrimaryColor);
                arrayshare[i].setTextSize(headingsFontsize);
            }
        }else if(layout==R.layout.layout_view_five){
            String share="&#xf2dc";
            String valHexStrshare=share.replace("&#x","").replace(";","");
            long valLongShare=Long.parseLong(valHexStrshare,16);
            LinearLayout parent=view.findViewById(R.id.fiveTransparent);
            TextView title=view.findViewById(R.id.fiveHeading);
            TextView body=view.findViewById(R.id.fiveBody);
            TextView icon=view.findViewById(R.id.fiveIcon);
            Button btn=view.findViewById(R.id.fiveButton);

            icon.setText((char)valLongShare+"");
            icon.setTypeface(fa);

            title.setTextColor(Color.WHITE);
            title.setTypeface(ElementFragment.elementFont(getActivity(), headingFontName, headingFontWeight));
            title.setTextSize(headingsFontsize);
            body.setTextColor(Color.WHITE);
            body.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            body.setTextSize(bodyFontsize);

            parent.setBackgroundColor(theSecondaryColorLight);
            parent.setAlpha(0.8f);

            btn.setTextColor(Color.WHITE);
            btn.setBackgroundColor(thePrimaryColor);
            btn.setTypeface(ElementFragment.elementFont(getActivity(), buttonFontName, buttonFontWeight));
            btn.setTextSize(buttonFontSize);
        }else if(layout==R.layout.layout_view_six){
            BottomNavigationView bottomNavigationView=view.findViewById(R.id.bottomNav);
            bottomNavigationView.setBackgroundColor(theSecondaryColorLight);
            bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
            bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
            TextView productName=view.findViewById(R.id.productName);
            TextView productPrice=view.findViewById(R.id.productPrice);
            TextView productDescription=view.findViewById(R.id.productDescription);
            Button buyNow=view.findViewById(R.id.buyNow);



            productName.setTextColor(thePrimaryColor);
            productName.setTypeface(ElementFragment.elementFont(getActivity(), headingFontName, headingFontWeight));
            productName.setTextSize(headingsFontsize);

            productPrice.setTextColor(thePrimaryColor);
            productPrice.setTypeface(ElementFragment.elementFont(getActivity(), headingFontName, headingFontWeight));
            productPrice.setTextSize(bodyFontsize);

            productDescription.setTextColor(theSecondaryColorDark);
            productDescription.setTypeface(ElementFragment.elementFont(getActivity(), bodyFontName, bodyFontWeight));
            productDescription.setTextSize(bodyFontsize);


            buyNow.setTextColor(Color.WHITE);
            buyNow.setBackgroundColor(thePrimaryColor);
            buyNow.setTypeface(ElementFragment.elementFont(getActivity(), buttonFontName, buttonFontWeight));
            buyNow.setTextSize(buttonFontSize);
        }
        return view;
    }

}
