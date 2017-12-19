package com.lst.burns.weather.gson;

import com.google.gson.annotations.SerializedName;

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond_txt")
    public String info;

    @SerializedName("cloud")
    public String cloud;
    @SerializedName("cond_code")
    public String conditionCode;
    @SerializedName("fl")
    public String flName;
    @SerializedName("hum")
    public String huminate;
    @SerializedName("pcpn")
    public String pcpnDegree;
    @SerializedName("pres")
    public String presCode;
    @SerializedName("vis")
    public String visCode;
    @SerializedName("wind_deg")
    public String windDegree;
    @SerializedName("wind_dir")
    public String windDirection;
    @SerializedName("wind_sc")
    public String windSource;
    @SerializedName("wind_spd")
    public String windSpeed;


}