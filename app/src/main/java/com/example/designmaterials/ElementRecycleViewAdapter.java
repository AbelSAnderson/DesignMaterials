package com.example.designmaterials;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.designmaterials.javabeans.Element;

import java.util.ArrayList;

public class ElementRecycleViewAdapter extends RecyclerView.Adapter {

    private final String ELEMENT = "Element";

    private ArrayList<Element> elements;

    public ElementRecycleViewAdapter(ArrayList<Element> elements) {
        this.elements = elements;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_element, null);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Element element = elements.get(position);
        ((ElementViewHolder) holder).getName().setText(element.getName());
    }

    @Override
    public int getItemCount() {
        if (elements != null) {
            return elements.size();
        }
        return 0;
    }

    class ElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;

        ElementViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.elementName);
        }

        public TextView getName() {
            return name;
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(ELEMENT, elements.get(getAdapterPosition()));
            Navigation.findNavController(view).navigate(R.id.action_nav_elements_to_elementFragment, bundle);
        }
    }
}