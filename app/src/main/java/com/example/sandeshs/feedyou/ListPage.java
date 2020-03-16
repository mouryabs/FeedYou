package com.example.sandeshs.feedyou;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListPage extends AppCompatActivity {
    TextView heading;
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter;
  //  ParseUser currentUser = ParseUser.getCurrentUser();
    //String username = currentUser.getUsername().toString();
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Donors");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpage);
        listview = (ListView)findViewById(R.id.listView);
        heading = (TextView) findViewById(R.id.tvll);
        query.whereEqualTo("Flag", 1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e == null)
                {
                 //   Toast.makeText(ListPage.this, list.size(), Toast.LENGTH_SHORT).show();
                    final ArrayList<String> lists = new ArrayList<String>(list.size());
                    for(int i=0 ; i<list.size();++i)
                    {
                        lists.add(list.get(i).get("Name").toString()+"    "+list.get(i).get("PickupTime").toString());
                        Log.d("checking",list.get(i).toString());
                    }
                   final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview,lists);
                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, final View view,
                                                final int position, long id) {
                            final String item = (String) parent.getItemAtPosition(position);
                            view.animate().setDuration(500).alpha(0).withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(),"Selected"+position,Toast.LENGTH_SHORT).show();
                                            adapter.notifyDataSetChanged();
                                            view.setAlpha(1);
                                            startActivity(new Intent(ListPage.this, Confirm.class));

                                        }
                                    });
                        }

                    });

                }
                else
                {
                    Log.d("error", "something went wrong!");
                }
            }
        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}



