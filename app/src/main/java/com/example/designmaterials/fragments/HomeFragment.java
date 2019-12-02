package com.example.designmaterials.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.designmaterials.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Typeface grotesque = Typeface.createFromAsset(getActivity().getAssets(), "grotesque.ttf");
        Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), "opensans.ttf");

        // Inflate the onCreateLayout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView title = view.findViewById(R.id.textView5);
        TextView desc = view.findViewById(R.id.textView6);

        title.setTypeface(grotesque);
        title.setTextSize(32);

        desc.setTypeface(roboto);
        desc.setTextSize(18);

        Button designButton = view.findViewById(R.id.designButton);

        designButton.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.button_in_right));

        designButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_design);
            }
        });

        return view;
    }
}