package com.example.mtelecom.helper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.net.URLEncoder;

public class Constants {
    public static int SPLASH_SCREEN_TIME_OUT   =   1500;

//    Use this context for selecting text of app's language
    public static Context LANGUAGE_CONTEXT   =   null;


    public static void sendWhatsappMsg(String number, String msg, Context context){
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            String url = "https://api.whatsapp.com/send?phone="+ number +"&text=" + URLEncoder.encode(msg, "UTF-8");
            intent.setPackage("com.whatsapp");
            intent.setData(Uri.parse(url));
            if (intent.resolveActivity(packageManager) != null) {
                context.startActivity(intent);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
