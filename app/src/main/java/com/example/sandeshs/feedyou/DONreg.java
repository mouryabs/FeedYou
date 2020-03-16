package com.example.sandeshs.feedyou;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;


public class DONreg extends AppCompatActivity {
    TextView head;
    EditText name,email,phno,addr,picaddr,time;
    Button reg;
   SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donreg);
        head=(TextView)findViewById(R.id.tvdreg);
        name=(EditText)findViewById(R.id.etname);
        email=(EditText)findViewById(R.id.etm);
        phno=(EditText)findViewById(R.id.etp);
        addr=(EditText)findViewById(R.id.etaddr);
        reg=(Button)findViewById(R.id.btreg1);
        picaddr=(EditText)findViewById(R.id.etpickup);
        time=(EditText)findViewById(R.id.etime);
       // db=openOrCreateDatabase("FoodDB",MODE_PRIVATE,null);
//      db.execSQL("CREATE TABLE donor(Name VARCHAR,Email VARCHAR,Phno VARCHAR,Address VARCHAR,Password VARCHAR);");
        //   db.execSQL("INSERT INTO donor VALUES('"+name.getText()+"','"+email.getText()+"','"+phno.getText()+"','"+addr.getText()+"','"+pass.getText()+"');");
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s1=name.getText().toString();
                final String s2=email.getText().toString();
                final String s3=phno.getText().toString();
                final String s4=addr.getText().toString();
                final String s5=picaddr.getText().toString();
                final String s6=time.getText().toString();
                final Integer i1=new Integer(1);
                ParseObject donor = new ParseObject("Donors");
                donor.put("Name", s1);
                donor.put("Email", s2);
                donor.put("PhoneNumber", s3);
                donor.put("Address", s4);
                donor.put("PickupAddress",s5);
                donor.put("PickupTime",s6);
                donor.put("Flag",i1);
                donor.saveInBackground();
                Context context = getApplicationContext();
                CharSequence text = "Thank you for donating!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
              //  db.execSQL("INSERT INTO donor VALUES('"+name.getText()+"','"+email.getText()+"','"+phno.getText()+"','"+addr.getText()+"','"+pass.getText()+"');");
                startActivity(new Intent(DONreg.this, SecondPage.class));


            }
        });

    }


}
