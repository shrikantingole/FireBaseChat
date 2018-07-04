package com.androidchatapp;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.TextView;

public class Utils
{
    public static final  String AVENIR_LIGHT="avenir-light";
    public static final  String AVENIR_MEDIUM="Avenir-Medium";
    public static void setFontTxt(TextView tv, Context context,String font)
    {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/"+font+".ttf");
        tv.setTypeface(tf);
    }

    public static void setFontEdt(EditText tv, Context context, String font)
    {
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/"+font+".ttf");
        tv.setTypeface(tf);
    }


}
