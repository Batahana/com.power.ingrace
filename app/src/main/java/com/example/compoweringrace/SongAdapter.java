package com.example.compoweringrace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    private LayoutInflater inflater;

    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_song, parent, false);
        }

        // Get the song at the current position
        Song song = getItem(position);

        // Bind the song data to the views
        TextView textSongNumber = convertView.findViewById(R.id.textSongNumber);
        TextView textSongTitle = convertView.findViewById(R.id.textSongTitle);
       // TextView textSongDetails = convertView.findViewById(R.id.textSongDetails);

        textSongNumber.setText(song.getNumber());
        textSongTitle.setText(song.getTitle());
      //  textSongDetails.setText(song.getDetail());

        return convertView;
    }
}