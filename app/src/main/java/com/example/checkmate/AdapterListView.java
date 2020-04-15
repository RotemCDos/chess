package com.example.checkmate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.RequiresApi;

/**
 * Created by Lenovo on 25/03/2019.
 */

public class AdapterListView extends BaseAdapter
{
    public ArrayList<HashMap<String,String>> list;
    private Activity activity;

    public AdapterListView(Activity activity, ArrayList<HashMap<String,String>> list)
    {
        super();
        this.activity =activity;
        this.list =list;
    }
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        // get object in place i
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        LayoutInflater inflater = activity.getLayoutInflater();
        viewHolder = new ViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.customrow, null);
            viewHolder.col2 = (TextView) convertView.findViewById(R.id.playerName);
            viewHolder.col1 = (TextView) convertView.findViewById(R.id.score);
            viewHolder.col3 = (TextView) convertView.findViewById(R.id.color);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        HashMap<String, String> map = list.get(i);

        //Design the score list
        viewHolder.col1.setText(map.get(leaderboards.FIRST_COLUMN));

        viewHolder.col1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //set the text view size
        if (i == 0) {
            viewHolder.col1.setTextSize(22);
            viewHolder.col1.setTextColor(Color.RED);
        }
        else {
            viewHolder.col1.setTextSize(20);

        }

        //Design the score list
        viewHolder.col2.setText(map.get(leaderboards.SECOND_COLUMN));

        viewHolder.col2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //set the text view size
        if(i == 0) {
            viewHolder.col2.setTextSize(22);
            viewHolder.col2.setTextColor(Color.RED);
        }
        else {
            viewHolder.col2.setTextSize(20);
        }

        viewHolder.col3.setText(map.get(leaderboards.THIRD_COLUMN));

        viewHolder.col3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //set the text view size
        if(i == 0) {
            viewHolder.col3.setTextSize(22);
            viewHolder.col3.setTextColor(Color.RED);
        }
        else {
            viewHolder.col3.setTextSize(20);
        }

        return convertView;
    }


    private class ViewHolder
    {
        TextView col1; // text view column in list view
        TextView col2; // text view column in list view
        TextView col3;
    }


}
