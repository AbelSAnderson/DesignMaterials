package com.example.designmaterials.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.designmaterials.ElementRecycleViewAdapter;
import com.example.designmaterials.R;
import com.example.designmaterials.javabeans.Element;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementsFragment extends Fragment {


    public ElementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_elements, container, false);

        ArrayList<Element> elements = new ArrayList<>();

        //TODO: Externalize These text elements on when we finish thinning them out
        //Text Elements
        elements.add(new Element(getString(R.string.elementHeading), new TextView(getContext()), "title"));
        elements.add(new Element(getString(R.string.elementBody), new TextView(getContext()), "body"));
//        elements.add(new Element("Plain Text", new EditText(getContext())));
//        elements.add(new Element("AutoCompleteTextView", new AutoCompleteTextView(getContext())));
//        elements.add(new Element("MultiAutoCompleteTextView", new MultiAutoCompleteTextView(getContext())));
//        elements.add(new Element("CheckedTextView", new CheckedTextView(getContext())));
//        elements.add(new Element("TextInputLayout", new TextInputLayout(getContext())));

        //Buttons
        elements.add(new Element(getString(R.string.elementButtons), new Button(getContext()), "button"));
//        elements.add(new Element("ImageButton", new ImageButton(getContext())));
//        elements.add(new Element("ChipGroup", new ChipGroup(getContext())));

        //Adding Chip Breaks the Program; Removing for now. - Abel
        //elements.add(new Element("Chip", new Chip(getContext())));

//        elements.add(new Element("CheckBox", new CheckBox(getContext())));
//        elements.add(new Element("RadioGroup", new RadioGroup(getContext())));
//        elements.add(new Element("RadioButton", new RadioButton(getContext())));
//        elements.add(new Element("ToggleButton", new ToggleButton(getContext())));
//        elements.add(new Element("Switch", new Switch(getContext())));
//        elements.add(new Element("FloatingActionButton", new FloatingActionButton(getContext())));

        //Widgets
//        elements.add(new Element("View", new View(getContext())));
//        elements.add(new Element("ImageView", new ImageView(getContext())));
//        elements.add(new Element("WebView", new WebView(getContext())));
//        elements.add(new Element("VideoView", new VideoView(getContext())));
//        elements.add(new Element("CalendarView", new CalendarView(getContext())));
//        elements.add(new Element("ProgressBar", new ProgressBar(getContext())));
//        elements.add(new Element("SeekBar", new SeekBar(getContext())));
//        elements.add(new Element("RatingBar", new RatingBar(getContext())));
//        elements.add(new Element("SearchView", new SearchView(getContext())));
//        elements.add(new Element("TextureView", new TextureView(getContext())));
//        elements.add(new Element("SurfaceView", new SurfaceView(getContext())));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewElements);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ElementRecycleViewAdapter(elements));

        Button viewButton = view.findViewById(R.id.viewsButton);

        viewButton.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.button_in_right));

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_elements_to_nav_views);
            }
        });

        return view;
    }
}