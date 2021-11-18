package com.example.mtelecom.helper;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    private static final String LANGUAGE_FILE_NAME = "PREFERRED_LANGUAGE";
    private static Context mainContext = null;

    private LocaleHelper(){}

    public static Context getLocaleContext(Context context){
        if(mainContext != null){
            return mainContext;
        }
        else {
            SharedPreferences sharedpreferences = context.getSharedPreferences(LANGUAGE_FILE_NAME, Context.MODE_PRIVATE);
            setLocale(context,sharedpreferences.getString(SELECTED_LANGUAGE,"en"));
            return mainContext;
        }
    }

    // the method is used to set the language at runtime
    public static void setLocale(Context context, String language) {
        persist(context.getApplicationContext(), language);

        // updating the language for devices above android nougat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context.getApplicationContext(), language);
        }
        // for devices having lower version of android os
        updateResourcesLegacy(context.getApplicationContext(), language);
    }

    private static void persist(Context context, String language) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(LANGUAGE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.commit();
    }

    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    @TargetApi(Build.VERSION_CODES.N)
    private static void updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        mainContext = context.createConfigurationContext(configuration);
    }


    @SuppressWarnings("deprecation")
    private static void updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        mainContext = context;
    }
}
