package com.beatsync.app.service;
public class SongNotFoundException extends RuntimeException {
 public SongNotFoundException(long id) { super("Song not found: " + id); }
}
