package com.example.designmaterials.javabeans;

import android.view.View;

import java.io.Serializable;

public class Element implements Serializable {
    private String name;

    private View viewElement;

    public Element(String name, View viewElement, String tag) {
        this.name = name;
        this.viewElement = viewElement;
        this.viewElement.setTag(tag);

    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return viewElement.getTag().toString();
    }

    public View getViewElement() {
        return viewElement;
    }

    public String toString() {
        return this.getViewElement().toString();
    }
}
