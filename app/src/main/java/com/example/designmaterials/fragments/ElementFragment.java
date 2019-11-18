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
import android.widget.LinearLayout;
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
//        if(vv instanceof Button){
//            (Button)vv.setText("Click me");
//        }
        vv.setMinimumWidth(100);
        vv.setMinimumHeight(100);
        vv.setBackgroundColor(thePrimaryColor);


        linearLayout.addView(vv);


        return view;
    }

}
