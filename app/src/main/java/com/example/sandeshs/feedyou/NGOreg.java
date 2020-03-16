package com.example.sandeshs.feedyou;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class NGOreg extends AppCompatActivity {
    TextView heading;
    EditText orgname,email,phone,address,uniqcode,passw;
    Button register;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoreg);
      // db =openOrCreateDatabase("FoodDB",MODE_PRIVATE,null);
//        db.execSQL("CREATE TABLE receiver(OrgName VARCHAR,Email VARCHAR,Phone VARCHAR,Address VARCHAR,UID VARCHAR,Password VARCHAR);");
        heading=(TextView)findViewById(R.id.tvlt);
        orgname=(EditText)findViewById(R.id.etnn);
        email=(EditText)findViewById(R.id.etmai);
        phone=(EditText)findViewById(R.id.etphn);
        address=(EditText)findViewById(R.id.etpad);
        uniqcode=(EditText)findViewById(R.id.unqcd);
        passw=(EditText)findViewById(R.id.etpass);
        register=(Button)findViewById(R.id.butreg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=orgname.getText().toString();
                final String emailstr=email.getText().toString();
                final String phonestr=phone.getText().toString();
                final String addrstr=address.getText().toString();
                final String uniqstr=uniqcode.getText().toString();
                final String passstr=passw.getText().toString();
           /*     ParseObject r = new ParseObject("Receiver");
                r.put("Name", s1);
                r.put("Email", s2);
                r.put("PhoneNumber", s3);
                r.put("Address", s4);
                r.put("UniqCode", s5);
                r.put("Password", s6);
                r.saveInBackground(); */
                ParseUser user = new ParseUser();
                user.setUsername(emailstr);
                user.setPassword(passstr);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "Thank you ;)", Toast.LENGTH_LONG).show();
                            ParseObject r = new ParseObject("Receiver");
                            r.put("Name", name);
                            r.put("Email", emailstr);
                            r.put("PhoneNumber", phonestr);
                            r.put("Address", addrstr);
                            r.put("UniqCode", uniqstr);
                            r.put("Password", passstr);
                            r.saveInBackground();

                        } else {
                            Toast.makeText(NGOreg.this, "Username exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

              //  db.execSQL("INSERT INTO receiver VALUES('"+orgname.getText()+"','"+email.getText()+"','"+phone.getText()+"','"+address.getText()+"','"+uniqcode.getText()+"','"+passw.getText()+"');");
                startActivity(new Intent(NGOreg.this, SecondPage.class));
            }
        });
    }


}
