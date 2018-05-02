package com.centralesupelec.ipfs.ipfsplayer;

import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Songslist extends Activity {
    /** Called when the activity is first created. */

    private TextView name1;
    private TextView name2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songsinpl);

        ListView list = (ListView) findViewById(R.id.songs_pl);
        list.setClickable(true);

        final List<ItemSongPlaylist> ListOfItemSongs = new ArrayList<ItemSongPlaylist>();
        ListOfItemSongs.add(new ItemSongPlaylist("Supélec est là", "Guillaume Debournoux", "SMS"));
        ListOfItemSongs.add(new ItemSongPlaylist("Cloporte", "Damian Py", "Vrai Ingénieur"));
        ListOfItemSongs.add(new ItemSongPlaylist("Go Top 1", "allez", "go top 1 srx"));

        ItemSongPlaylistAdapter adapter = new ItemSongPlaylistAdapter(this, ListOfItemSongs);


        name1 = (TextView) findViewById(R.id.playlist_name);
        name2 = (TextView) findViewById(R.id.playlist_name2);
        name1.setText(MainActivity.title2.toUpperCase());
        name2.setText(MainActivity.title2.toUpperCase());

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                System.out.println("sadsfsf");
                showToast(ListOfItemSongs.get(position).getSongname());
            }
        });

        list.setAdapter(adapter);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
