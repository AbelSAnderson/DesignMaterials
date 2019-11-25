package com.example.designmaterials.javabeans;

public class Attribution {

    private String title;
    private String name;
    private String linkText;
    private String link;

    public Attribution(String title, String name, String linkText, String link) {
        this.title = title;
        this.name = name;
        this.linkText = linkText;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getLinkText() {
        return linkText;
    }

    public String getLink() {
        return link;
    }
}