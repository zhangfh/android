package com.lst.burns.weather.gson;

import com.google.gson.annotations.SerializedName;

public class Suggestion {

    @SerializedName("txt")
    public String info;
    @SerializedName("brf")
    public String brief;

    @SerializedName("type")
    public String type;

}