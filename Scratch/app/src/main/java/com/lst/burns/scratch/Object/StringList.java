package com.lst.burns.scratch.Object;

import android.util.Log;

import com.lst.burns.scratch.util.Constant;

import java.util.HashMap;


public class StringList {

    private String Contents;
    private HashMap<String, String[]>  list = new HashMap<>();
    private String key;

    public  StringList(String contents){
        Contents = contents;

    }

    public boolean IsStringList(){
        if (!Contents.contains(", ")){
            return false;
        }else {
            Log.i("ZFH","this is stringlist");
            return true;
        }
    }

    public void Parse(){
       String[] group =  this.Contents.split("=");

        //remove whitespace in begin and end.
        key =  group[0].trim();
        String value = group[1].trim();
        String[] values = value.split(", ");


        String[] properties = new String[values.length];
        for (int i = 0; i<values.length; i++)
        {
            String p1 = values[i].replace("\"","");
            String p2 = "";
            if (i == values.length - 1){
                p2 = p1.replace(";","");
            }else{
                p2 = p1;
            }
            properties[i] = p2;
        }
        list.put(key,properties);
        Log.i("ZFH","key :"+key+":value:"+list);
    }

    public String[] getValue()
    {
        return list.get(key);
    }
}