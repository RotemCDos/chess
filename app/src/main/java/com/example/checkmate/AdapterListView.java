package com.example.checkmate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.RequiresApi;

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

            viewHolder.col5 = (TextView) convertView.findViewById(R.id.playerName2);
            viewHolder.col4 = (TextView) convertView.findViewById(R.id.score2);
            viewHolder.col6 = (TextView) convertView.findViewById(R.id.color2);

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

        //////////////////////////////////////////////////////////////////////////////

        //Design the score list
        viewHolder.col4.setText(map.get(leaderboards.FOURTH_COLUMN));

        viewHolder.col4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //set the text view size
        if (i == 0) {
            viewHolder.col4.setTextSize(22);
            viewHolder.col4.setTextColor(Color.RED);
        }
        else {
            viewHolder.col4.setTextSize(20);

        }

        //Design the score list
        viewHolder.col5.setText(map.get(leaderboards.FIFTH_COLUMN));

        viewHolder.col5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //set the text view size
        if(i == 0) {
            viewHolder.col5.setTextSize(22);
            viewHolder.col5.setTextColor(Color.RED);
        }
        else {
            viewHolder.col5.setTextSize(20);
        }

        viewHolder.col6.setText(map.get(leaderboards.SIXTH_COLUMN));

        viewHolder.col6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //set the text view size
        if(i == 0) {
            viewHolder.col6.setTextSize(22);
            viewHolder.col6.setTextColor(Color.RED);
        }
        else {
            viewHolder.col6.setTextSize(20);
        }

        return convertView;
    }

    private class ViewHolder {
        TextView col1; // text view column in list view
        TextView col2; // text view column in list view
        TextView col3;
        TextView col4;
        TextView col5;
        TextView col6;
    }
}
