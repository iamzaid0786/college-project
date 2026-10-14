package com.beatsync.app.model;
/** Immutable domain model for one track in the catalog. */
public record Song(Long id, String title, String artist, String album, String genre, String audioUrl) {}
