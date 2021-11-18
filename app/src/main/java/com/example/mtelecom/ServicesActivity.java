package com.example.mtelecom;


import static com.example.mtelecom.helper.Constants.LANGUAGE_CONTEXT;
import static com.example.mtelecom.helper.Constants.sendWhatsappMsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mtelecom.helper.LocaleHelper;

public class ServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_logic);

        //Status bar title
        getSupportActionBar().setTitle(LANGUAGE_CONTEXT.getString(R.string.service_view_name));
    }

    public void pressedf(View view) {
        sendWhatsappMsg(getString(R.string.mobile_number),"hi",this);
    }

    private CharSequence menuIconWithText(Drawable r, String title) {

        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, menuIconWithText(getResources().getDrawable(R.drawable.ic_outline_info_24), LANGUAGE_CONTEXT.getString(R.string.menu_option_about_us)));
        menu.add(0, 2, 2, menuIconWithText(getResources().getDrawable(R.drawable.ic_outline_contact_phone_24), LANGUAGE_CONTEXT.getString(R.string.menu_option_contact_us)));
        menu.add(0, 3, 3, menuIconWithText(getResources().getDrawable(R.drawable.ic_outline_my_location_24), LANGUAGE_CONTEXT.getString(R.string.menu_option_locate_me)));
        menu.add(0, 4, 4, menuIconWithText(getResources().getDrawable(R.drawable.ic_outline_translate_24), LANGUAGE_CONTEXT.getString(R.string.menu_option_change_language)));
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(ServicesActivity.this, "Profile is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(ServicesActivity.this, "Add New User is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                Toast.makeText(ServicesActivity.this, "Switch Profile is Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case 4:
                Toast.makeText(ServicesActivity.this, "Sign Out is Clicked", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}