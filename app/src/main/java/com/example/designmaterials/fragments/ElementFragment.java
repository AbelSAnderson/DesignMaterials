package com.example.designmaterials.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.TextView;

import com.example.designmaterials.R;
import com.example.designmaterials.javabeans.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ElementFragment extends Fragment {


    public ElementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_element, container, false);
        SharedPreferences sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
        int thePrimaryColor=sharedPreferences.getInt("primaryColor",123);
        LinearLayout linearLayout=view.findViewById(R.id.SingleElementView);
        Bundle bundle=this.getArguments();

        Element elemnt= (Element) bundle.getSerializable("Element");

        View vv = elemnt.getViewElement();

        linearLayout.removeAllViews();
        if(vv instanceof Button){
            Button btn=new Button(getActivity());
            btn.setText("Click me nice");
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundColor(thePrimaryColor);
            linearLayout.addView(btn);
        }else if(vv instanceof MultiAutoCompleteTextView){
            MultiAutoCompleteTextView txt=new MultiAutoCompleteTextView(getActivity());
            txt.setText("The MultiAutoCompleteTextView Lorem ipsum has a lot of characters");
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            linearLayout.addView(txt);
        }else if(vv instanceof AutoCompleteTextView){
            AutoCompleteTextView txt=new AutoCompleteTextView(getActivity());
            txt.setText("The AutoCompleteTextView Lorem ipsum has a lot of characters");
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            linearLayout.addView(txt);
        }else if(vv instanceof EditText){
            EditText txt=new EditText(getActivity());
            txt.setText("The Edit text Lorem ipsum has a lot of characters");
            txt.setTextSize(18);
            txt.setTextColor(thePrimaryColor);
            linearLayout.addView(txt);
        }else if(vv instanceof TextView){
            TextView txt=new TextView(getActivity());
            txt.setText("This text is so nice and has a lot of characters");
            txt.setTextSize(22);
            txt.setTextColor(thePrimaryColor);
            linearLayout.addView(txt);
            Log.wtf("wtf","wtf again");
        }




//        linearLayout.addView(vv);


        return view;
    }

}
