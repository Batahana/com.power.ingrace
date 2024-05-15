package com.example.compoweringrace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Bookmarks extends AppCompatActivity {
    private static final String FAVORITES_PREF = "favorites_pref";
    private static final String FAVORITES_KEY = "favorites_key";

    private ArrayList<Song> favoriteSongs;
    private SongAdapter songAdapter;
    private ListView listViewFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        listViewFavorites = findViewById(R.id.listViewFavorites);

        favoriteSongs = (ArrayList<Song>) loadFavorites();
        songAdapter = new SongAdapter(this, favoriteSongs);
        listViewFavorites.setAdapter(songAdapter);
    }

    private List<Song> loadFavorites() {
        SharedPreferences prefs = getSharedPreferences(FAVORITES_PREF, MODE_PRIVATE);
        String favoritesJson = prefs.getString(FAVORITES_KEY, null);
        if (favoritesJson != null) {
            Type type = new TypeToken<ArrayList<Song>>() {}.getType();
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
        Intent intent = new Intent(this, Bookmarks.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}