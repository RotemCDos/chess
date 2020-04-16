package com.example.checkmate;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Players> {
    Context context;
    List<Players> objects;

    public ScoreAdapter(Context context, int resource, int textViewResourceId, List<Players> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.customrow, parent, false);

        TextView tvName = (TextView) view.findViewById(R.id.playerName);
        TextView tvScore = (TextView) view.findViewById(R.id.score);
        TextView tvColor = (TextView) view.findViewById(R.id.color);

        TextView tvName2 = (TextView) view.findViewById(R.id.playerName2);
        TextView tvScore2 = (TextView) view.findViewById(R.id.score2);
        TextView tvColor2 = (TextView) view.findViewById(R.id.color2);

        Players temp = objects.get(position);

        tvScore.setText(String.valueOf(temp.getScoreW()));
        tvName.setText(temp.getNameW());
        tvColor.setText(temp.getColorW());

        tvScore2.setText(String.valueOf(temp.getScoreB()));
        tvName2.setText(temp.getNameB());
        tvColor2.setText(temp.getColorB());

        return view;

    }

}