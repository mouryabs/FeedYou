package com.example.sandeshs.feedyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class SecondPage extends AppCompatActivity {

    // public SQLiteDatabase db=openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE,null);

    EditText email, pwd;
    TextView heading, dtv1;
    Button login, dlogin, reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
        //  Parse.enableLocalDatastore(this);
        // Parse.initialize(this, "hs9KrpRcqY5CjyPZ9W7iOIHLU9VeZPh4eerURHUw", "hnkMWqJYQlDXkkMQ21iejI9yj2nSnvq2OVkpTPBz");
        //  ParseInstallation.getCurrentInstallation().saveInBackground();
        heading = (TextView) findViewById(R.id.tvhead);
        dtv1 = (TextView) findViewById(R.id.dtv);
        email = (EditText) findViewById(R.id.etmail);
        pwd = (EditText) findViewById(R.id.etpd);
        login = (Button) findViewById(R.id.btlogin);
        reg = (Button) findViewById(R.id.btreg);
        dlogin = (Button) findViewById(R.id.btdlogin);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondPage.this, NGOreg.class));
            }
        });
        dlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondPage.this, DONreg.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiverLogin(v);
            }
        });


    }

    public void receiverLogin(View view) {
       /* ParseQuery<ParseObject> query1 = ParseQuery.getQuery("receiver");
        query1.whereEqualTo("email", email);
        {
            query1.whereEqualTo("password", pwd);
            query1.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> donor, ParseException e) {
                    if (e == null) {
                        Log.d("data", "Retrieved ");
                        Toast.makeText(getApplicationContext(), "Login!", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(SecondPage.this, ListPage.class));
                    } else {
                        Log.d("receiver", "Error: " + e.getMessage());
                        Toast.makeText(getApplicationContext(), "Did not login!", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }

*/
        ParseUser.logInInBackground(email.getText().toString(),pwd.getText().toString(),
                new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // If user exist and authenticated, send user to Welcome.class
                            Intent intent = new Intent(
                                    SecondPage.this,
                                    ListPage.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Logged in",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Invalid username/password. Please try again.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}



