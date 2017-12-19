package com.lst.burns.weather.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("cond_code_d")
    public String conditionCodeD;
    @SerializedName("cond_code_n")
    public String conditionCodeN;
    @SerializedName("cond_txt_d")
    public String conditionText;
    @SerializedName("date")
    public String dateString;
    @SerializedName("tmp_min")
    public String temperature_min;
    @SerializedName("tmp_max")
    public String temperature_max;


}