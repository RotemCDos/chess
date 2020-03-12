package com.example.checkmate;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Player> {

    Context context;
    List<Player> objects;
    public ScoreAdapter(Context context, int resource, int textViewResourceId, List<Player> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.customrow,parent,false);

        TextView tvName = (TextView)view.findViewById(R.id.tvName);
        TextView tvScore = (TextView)view.findViewById(R.id.tvScore);
        Button btnDelete = (Button)view.findViewById(R.id.btnDelete);

        Player temp = objects.get(position);


        tvScore.setText(String.valueOf(temp.getScore()));
        tvName.setText(temp.getName());




        return view;
    }

}
