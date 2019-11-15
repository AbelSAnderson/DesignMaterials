package com.example.designmaterials.javabeans;

import android.view.View;

import java.io.Serializable;

public class Element implements Serializable {
    private String name;
    private View viewElement;

    public Element(String name, View viewElement) {
        this.name = name;
        this.viewElement = viewElement;
    }

    public String getName() {
        return name;
    }

    public View getViewElement() {
        return viewElement;
    }
}
