package com.lst.burns.weather.gson;

import com.google.gson.annotations.SerializedName;

public class Update {
    @SerializedName("loc")
    public String updateTime;
    @SerializedName("utc")
    public String utcTime;
}