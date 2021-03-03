package com.ktc.todyinfo.model;

public class Icon {

    private String name;
    private int imageId;

    public Icon(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
