package com.lst.burns.timeline;

public class Item {
    int resId;
    String text;
    public String getText() {
        return text;
    }

    public Item setText(String text) {
        this.text = text;
        return this;
    }

    public int getResId() {
        return resId;
    }

    public Item setResId(int resId) {
        this.resId = resId;
        return this;
    }



}