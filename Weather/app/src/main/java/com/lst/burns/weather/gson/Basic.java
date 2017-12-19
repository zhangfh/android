package com.lst.burns.weather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    @SerializedName("location")
    public String cityName;//location
    @SerializedName("cid")
    public String weatherId;
    @SerializedName("parent_city")
    public String parentCity;
    @SerializedName("admin_area")
    public String provinceCity;
    @SerializedName("cnty")
    public String CountryName;
    @SerializedName("lat")
    public String latitude;
    @SerializedName("lon")
    public String lontitude;
    @SerializedName("tz")
    public String timeZone;


}