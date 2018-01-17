package com.lst.burns.scratch.Object;

import android.util.Log;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DtsFile {

    private String mContents;
    private String mStrippedString;//save strip whitespace and \n
    private HashMap<String,String> rootMap = new HashMap<String,String>();
    public DtsFile(String contents)
    {
        mContents = contents;
    }

    public DtsFile Strip(){
        mStrippedString =  mContents.replaceAll("\\s+","");
        Log.i("ZFH","mContents " +  mStrippedString);
        return  this;
    }

    public DtsFile findRootCompatible()
    {
        int position = mStrippedString.indexOf("/{compatible");
        Log.i("ZFH","root " + position);

        boolean find_root = false;


        StringBuilder root_compatible = new StringBuilder();
        for(int i=0; i<mStrippedString.length(); i++)
        {
            if (find_root){
                if ( mStrippedString.charAt(i) == ';'){
                    if (find_root){
                        break;
                    }

                }else {
                    if ( mStrippedString.charAt(i) != '{')
                         root_compatible.append(mStrippedString.charAt(i));
                }
            }
            if ( mStrippedString.charAt(i) == '/'&& mStrippedString.charAt(i+1) == '{'){
                find_root = true;
            }

        }

        String[] group =  root_compatible.toString().split("=");
        rootMap.put(group[0],group[1]);
        Log.d("ZFH","find root map" + rootMap.get("compatible"));
        return  this;
    }
}