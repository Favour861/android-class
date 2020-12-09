package com.example.introclass;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        final String EMAIL_PARAM = "";
        Button proceedBtn = (Button) findViewById(R.id.proceedBtn);
        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailInp = (EditText) findViewById(R.id.emailInp);
                EditText phoneInp = (EditText) findViewById(R.id.phoneInp);

                String email = emailInp.getText().toString();
                String phone = phoneInp.getText().toString();

                UserModel user = new UserModel(0, email, phone);

                DatabaseHelper db = new DatabaseHelper(SignUpActivity.this);
                Boolean success = db.addUser(user);
                if(success){
                    Snackbar.make(v,"New User created successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }

//                Snackbar.make(v, user.toString(), Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


//                Intent dashboardIntent = new Intent(getApplicationContext(), DashboardActivity.class);
//                dashboardIntent.putExtra("EMAIL_PARAM", email);
//                dashboardIntent.putExtra("PHONE_PARAM", phone);
//
//                startActivity(dashboardIntent);


            }
        });
    }

//    public void showList(View view) {
//        Intent listIntent = new Intent(this, UserListActivity.class);
//        startActivity(listIntent);
//        DatabaseHelper db = new DatabaseHelper(this);
//    }
}