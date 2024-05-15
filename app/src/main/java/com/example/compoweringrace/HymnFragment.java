package com.example.compoweringrace;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HymnFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    // Declare the songs variable as a member variable
    private ArrayList<Song> songs;
    private String[] songTitles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hymns, container, false);

        // Fetch the string arrays from resources
        String[] songTitles = getResources().getStringArray(R.array.songNumber);
        String[] songNumbers = getResources().getStringArray(R.array.songTitle);
        String[] songDetail = getResources().getStringArray(R.array.songDetails);

        // Initialize the songs ArrayList
        songs = new ArrayList<>();

        // Add songs to the ArrayList
        for (int i = 0; i < songTitles.length; i++) {
            String songTitle = songTitles[i];
            String songNumber = songNumbers[i];
            String songDetails = songDetail[i];

            songs.add(new Song(songNumber, songTitle, songDetails));
        }

        // Create a custom adapter to populate the ListView
        SongAdapter adapter = new SongAdapter(requireContext(), songs);

        // Find the ListView in your fragment layout
        ListView listView = view.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = songs.get(position);
                String songNumber = song.getNumber();
                String songDetail = song.getDetail();
                String songTitle = song.getTitle();

                // Create an intent to start the DetailActivity
                Intent intent = new Intent(requireContext(), DetailActivity.class);
                intent.putExtra("songNumber", songNumber);
                intent.putExtra("songDetail", songDetail);
                intent.putExtra("songTitle", songTitle);
                startActivity(intent);
            }
        });

        // Set the adapter for the ListView
        listView.setAdapter(adapter);

        return view;
    }
}

