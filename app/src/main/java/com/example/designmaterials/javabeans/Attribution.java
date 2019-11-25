package com.example.designmaterials.javabeans;

public class Attribution {

    private String title;
    private String name;
    private String description;

    public Attribution(String title, String name, String description) {
        this.title = title;
        this.name = name;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
