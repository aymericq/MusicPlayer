package com.centralesupelec.ipfs.ipfsplayer;

public class ItemSongPlaylist {
    private String songname;
    private String artist;
    private String album;

    // Constructor for the class
    public ItemSongPlaylist (String songname, String artist, String album) {
        super();
        this.songname = songname;
        this.artist = artist;
        this.album = album;
    }

    // Getter and setter methods for all the fields.
    // Though you would not be using the setters for this example,
    // it might be useful later.
    public String getSongname() {
        return songname;
    }
    public void setSongname(String songname) {
        this.songname = songname;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
}
