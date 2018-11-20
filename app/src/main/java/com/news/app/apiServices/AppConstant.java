package com.news.app.apiServices;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AppConstant {

    public static String forecastApi = "http://api.apixu.com/v1/forecast.json?key=3e0a52bfbf144299928172516181911&q=Bangalore&days=1\n";
    public static String newsApi = "https://newsapi.org/v2/top-headlines?country=in&apiKey=0d3d0a22b1cf4a41a9dd3506b76bded6";


    @SuppressWarnings("deprecation")
    public static void showDialog(Context context, String title, String message) {
        AlertDialog successDialog = new AlertDialog.Builder(context).create();
        successDialog.setTitle(title);
        //successDialog.setIcon(R.drawable.error);
        successDialog.setMessage(message);
        successDialog.setButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        successDialog.show();

    }

    @SuppressWarnings("deprecation")
    public static void showDialogOnlyTitle(Context context, String title) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(title);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Close",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}
