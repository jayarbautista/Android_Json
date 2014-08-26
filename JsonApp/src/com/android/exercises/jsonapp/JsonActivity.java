package com.android.exercises.jsonapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class JsonActivity extends Activity {

	String myjsonstring;

	static final String TAG_NAME = "name";
	static String TAG_PHOTO = "image";

	JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		arraylist = new ArrayList<HashMap<String, String>>();
		 
        //ListView lv = getListView();
 

		new GetAddress().execute();
	}

	private class GetAddress extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			mProgressDialog = new ProgressDialog(JsonActivity.this);
			mProgressDialog.setMessage("Please wait...");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {

			// Reading text file from assets folder
			StringBuffer sb = new StringBuffer();
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(getAssets().open(
						"jsondemo.txt")));
				String temp;
				while ((temp = br.readLine()) != null)
					sb.append(temp);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close(); // stop reading
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			myjsonstring = sb.toString();
			
			try {
                JSONObject jsonObj = new JSONObject(myjsonstring);
                 
                // Getting JSON Array node
                jsonarray = jsonObj.getJSONArray("address_book");

                // looping through All Contacts
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonobject = jsonarray.getJSONObject(i);

                    // tmp hashmap for single contact
                    HashMap<String, String> book = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    book.put(TAG_NAME, jsonobject.getString(TAG_NAME));
                    book.put(TAG_PHOTO, jsonobject.getString(TAG_PHOTO));

                    // adding contact to contact list
                    arraylist.add(book);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			 // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(JsonActivity.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            
            // Close the progressdialog
            mProgressDialog.dismiss();
		}
	}
}
