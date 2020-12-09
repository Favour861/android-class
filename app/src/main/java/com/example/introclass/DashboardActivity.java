package com.example.introclass;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static java.net.Proxy.Type.HTTP;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent incoming = getIntent();
        String email = incoming.getStringExtra("EMAIL_PARAM");
        String phone = incoming.getStringExtra("PHONE_PARAM");

        EditText welcome = (EditText) findViewById(R.id.welcome);
        String newText = welcome.getText().toString() + " " + email;
        welcome.setText(newText);
    }

    public void openPage(View view) {
        //INTENT FOR OPENING A WEB BROWSER
//        Uri webpage = Uri.parse("http://gmail.com");
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
//
//        if(webIntent.resolveActivity(getPackageManager()) != null){
//            startActivity(webIntent);
//        }

        //INTENT FOR DIALING A PHONE NUMBER
//        Uri phoneCall = Uri.parse("tel:09046348295");
//        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, phoneCall);
//        startActivity(phoneIntent);

        //INTENT FOR SENDING MAIL
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.setType("HTTP.PLAIN_TEXT_TYPE");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, "afolabifavouroluwatobi@gmail.com");
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JUST A MAIL TEST");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, "THIS IS THE BODY");
//        emailIntent.putExtra(Intent.EXTRA_RESULT_RECEIVER, "beyondbaba@yahoo+++++++.com");
//
//
//        if(emailIntent.resolveActivity(getPackageManager()) != null){
//            startActivity(emailIntent);
//        }

        Uri webpage = Uri.parse("http://gmail.com");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

        String title = getResources().getString(R.string.chooserTitle);

        Intent chooser = Intent.createChooser(webIntent, title);
        if(webIntent.resolveActivity(getPackageManager()) != null){
            startActivity(chooser);
        }


    }
}