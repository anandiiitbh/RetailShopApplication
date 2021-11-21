package com.example.mtelecom;

import static com.example.mtelecom.helper.Constants.LANGUAGE_CONTEXT;
import static com.example.mtelecom.helper.Constants.sendWhatsappMsg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        //Status bar title
        getSupportActionBar().setTitle(LANGUAGE_CONTEXT.getString(R.string.menu_option_contact_us));

        setText();

        findViewById(R.id.mobileCall).setOnClickListener(this);
        findViewById(R.id.mailId).setOnClickListener(this);
        findViewById(R.id.sndWaMsg).setOnClickListener(this);

        ((TextView)findViewById(R.id.nameOfPerson)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0){
                    findViewById(R.id.sndWaMsg).setEnabled(true);
                }
                else {
                    findViewById(R.id.sndWaMsg).setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void setText(){
        ((TextView)findViewById(R.id.or1)).setText(LANGUAGE_CONTEXT.getString(R.string.or));
        ((TextView)findViewById(R.id.or2)).setText(LANGUAGE_CONTEXT.getString(R.string.or));
        ((TextView)findViewById(R.id.nameOfPerson)).setHint(LANGUAGE_CONTEXT.getString(R.string.name));
        ((Button)findViewById(R.id.sndWaMsg)).setText(LANGUAGE_CONTEXT.getString(R.string.send));
    }

    public void sendWaMsg(String name) {
        sendWhatsappMsg(getString(R.string.mobile_number),"hi M Telecom,\ni need help!!\nThanks\nRegards\n"+name,this);
    }

    public void callDriver() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.mobile_number).trim()));
        startActivity(intent);
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mobileCall: callDriver();
                break;
            case R.id.mailId: composeEmail(new String[] {getString(R.string.mail_id)}, "hi");
                break;
            case R.id.sndWaMsg: sendWaMsg(((TextView)findViewById(R.id.nameOfPerson)).getText().toString());
                break;

        }
    }
}