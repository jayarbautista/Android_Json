package com.android.exercises.jsonapp;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	
	Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    //ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();
 
    public ListViewAdapter(Context context,
            ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        //imageLoader = new ImageLoader(context);
    }

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	
	static class ViewHolder {
		TextView mName;
		ImageView mImage;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
			convertView = inflater.inflate(R.layout.activity_json, parent, false);
			
			holder = new ViewHolder();
			// Get the position
	        resultp = data.get(position);
	
	        holder.mName = (TextView)convertView.findViewById(R.id.name);
	        holder.mImage = (ImageView)convertView.findViewById(R.id.pic);
	
	        holder.mName.setText(resultp.get(JsonActivity.TAG_NAME));
	        switch(position){
	        case 0:
	        	holder.mImage.setImageResource(R.drawable.bill);
	        	break;
	        case 1:
	        	holder.mImage.setImageResource(R.drawable.james);
	        	break;
	        case 2:
	        	holder.mImage.setImageResource(R.drawable.andy);
	        	break;
	        }
	        
	        //imageLoader.DisplayImage(resultp.get(JsonActivity.TAG_PHOTO),mImage);
		}
		
		return convertView;
	}

}
