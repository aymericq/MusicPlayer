package com.centralesupelec.ipfs.ipfsplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.Button;

import com.centralesupelec.ipfs.ipfsplayer.fragments.DiscoverFragment;
import com.centralesupelec.ipfs.ipfsplayer.fragments.PlaylistsFragment;
import com.centralesupelec.ipfs.ipfsplayer.fragments.SearchFragment;
import com.centralesupelec.ipfs.ipfsplayer.fragments.SettingsFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private Fragment displayedFragment;
    private HashMap<String, Fragment> fragments;

    private TextView mTextMessage;
    private ViewFlipper mViewFlipper;
    private byte currentView;
    private Button btnPlayer;
    private ArrayList<HashMap<String, String>> playlist;

    private EditText etInput;
    private ImageButton btnAdd;
    private ListView lvItem;
    private ArrayList<String> itemArray;
    private ArrayAdapter<String> itemAdapter;

    ListView mainList;

    private SongsManager sm;

    private String[] listContent;
    public static MediaPlayer mp;
    public static String title;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_decouvrir:
                    mTextMessage.setText(R.string.title_decouvrir);
                    return true;
                case R.id.navigation_rechercher:
                    mTextMessage.setText(R.string.title_rechercher);
                    return true;
                case R.id.navigation_playlists:
                    mTextMessage.setText(R.string.title_playlists);
                    return true;
                case R.id.navigation_parametres:
                    mTextMessage.setText(R.string.title_parametres);
                    return true;
            }
            return false;
        }
    };

    ListView mainList;
    private String[] listContent;
    public static MediaPlayer mp;
    public static String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        displayedFragment = new DiscoverFragment();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.main_container, displayedFragment);
        ft.commit();

        sm = new SongsManager();
        playlist = sm.getPlayList();

        fragments = new HashMap<String, Fragment>();
        fragments.put("discover", displayedFragment);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    20);
        }



    }

    public void onNavBarClicked(View view) {
        String tag = (String) view.getTag();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch(tag) {
            case "discover":
                Fragment discoverFragment;
                if(!fragments.containsKey("discover")) {
                    discoverFragment = new DiscoverFragment();
                    fragments.put("discover", discoverFragment);
                }
                else {
                    discoverFragment = fragments.get("discover");
                }
                ft.replace(R.id.main_container, discoverFragment);
                currentView = 0;
                break;
            case "search":
                Fragment searchFragment;
                if(!fragments.containsKey("search")) {
                    searchFragment = new SearchFragment();
                    fragments.put("search", searchFragment);
                }
                else {
                    searchFragment = fragments.get("search");
                }
                ft.replace(R.id.main_container, searchFragment);
                currentView = 1;
                break;
            case "playlists":
                Fragment playlistsFragment;
                if(!fragments.containsKey("playlists")) {
                    playlistsFragment = new PlaylistsFragment();
                    fragments.put("playlists", playlistsFragment);
                }
                else {
                    playlistsFragment = fragments.get("playlists");
                }
                ft.replace(R.id.main_container, playlistsFragment);
                currentView = 2;
                break;
            case "settings":
                Fragment settingsFragment;
                if(!fragments.containsKey("settings")) {
                    settingsFragment = new SettingsFragment();
                    fragments.put("settings", settingsFragment);
                }
                else {
                    settingsFragment = fragments.get("settings");
                }
                ft.replace(R.id.main_container, settingsFragment);
                currentView = 3;
                break;
            default:
                // Stay on same fragment
                break;
        }
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mp != null) mp.release();
    }

    public void playSong(int songIndex) {
        mp = MediaPlayer.create(getApplicationContext(), Uri.parse(playlist.get(songIndex).get("songPath")));

        Intent playSongIntent = new Intent(getApplicationContext(), PlayerActivity.class);
        playSongIntent.putExtra("songIndex", songIndex);
        startActivityForResult(playSongIntent, 100);
    }

    public SongsManager getSongManager() {
        return sm;
    }
}
