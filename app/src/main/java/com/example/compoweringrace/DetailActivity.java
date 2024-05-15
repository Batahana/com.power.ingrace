package com.example.compoweringrace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    private static final String FAVORITES_PREF = "favorites_pref";
    private static final String FAVORITES_KEY = "favorites_key";

    private Intent intent;
    private TextView songTitleTextView;
    private TextView songDetailTextView;
    private ImageView favoriteButton;
    private List<Song> favoriteSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize the UI elements
        songTitleTextView = findViewById(R.id.textViewTitle);
        songDetailTextView = findViewById(R.id.textViewDetail);
        favoriteButton = findViewById(R.id.favoriteIcon);

        // Initialize the intent and the favorites list
        intent = getIntent();
        favoriteSongs = loadFavorites();

        // Check if the intent is not null before accessing its properties
        if (intent != null) {
            // Retrieve the song details from the intent
            String songNumber = intent.getStringExtra("songNumber");
            String songDetail = intent.getStringExtra("songDetail");
            String songTitle = intent.getStringExtra("songTitle");

            // Update the UI with the song details
            songTitleTextView.setText(songTitle);
            songDetailTextView.setText(songDetail);

            // Check if the song is already in the favorites list
            checkIfSongIsFavorite(songNumber);

            // Set the click listener for the favorite button
            favoriteButton.setOnClickListener(v -> addToFavorites(songNumber, songTitle, songDetail));
        } else {
            // Handle the case where the intent is null
            finish();
        }
    }

    private void addToFavorites(String songNumber, String songTitle, String songDetail) {
        // Create a new Song object
        Song song = new Song(songNumber, songTitle, songDetail);

        // Check if the song is already in the favorites list
        if (!favoriteSongs.contains(song)) {
            // Add the song to the favorites list
            favoriteSongs.add(song);
            saveFavorites(favoriteSongs);
            showSnackbar("Song added to favorites");
            favoriteButton.setImageResource(R.drawable.ic_full_heart);
        } else {
            showSnackbar("Song is already in favorites");
        }
    }

    private void checkIfSongIsFavorite(String songNumber) {
        // Check if the song is in the favorites list
        for (Song song : favoriteSongs) {
            if (song.getNumber().equals(songNumber)) {
                favoriteButton.setImageResource(R.drawable.ic_full_heart);
                return;
            }
        }

        // If the song is not in the favorites list, set the button to the unfilled icon
        favoriteButton.setImageResource(R.drawable.ic_borderless_heart);
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    private List<Song> loadFavorites() {
        SharedPreferences prefs = getSharedPreferences(FAVORITES_PREF, MODE_PRIVATE);
        String favoritesJson = prefs.getString(FAVORITES_KEY, null);
        if (favoritesJson != null) {
            Type type = new TypeToken<List<Song>>() {}.getType();
            return new Gson().fromJson(favoritesJson, type);
        }
        return new ArrayList<>();
    }

    private void saveFavorites(List<Song> favorites) {
        SharedPreferences prefs = getSharedPreferences(FAVORITES_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(FAVORITES_KEY, new Gson().toJson(favorites));
        editor.apply();

        // Notify the FavoritesActivity that the favorites have been updated
      //  Intent intent = new Intent(this, Bookmarks.class);
       // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
      //  startActivity(intent);
    }

}