package com.beatsync.app.repository;
import com.beatsync.app.model.Song;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
/** JDBC data-access layer. SQL is isolated from HTTP controllers and UI code. */
@Repository
public class SongRepository {
 private final JdbcTemplate jdbc;
 private final RowMapper<Song> mapper = (rs, row) -> new Song(rs.getLong("id"), rs.getString("title"), rs.getString("artist"), rs.getString("album"), rs.getString("genre"), rs.getString("audio_url"));
 public SongRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }
 public List<Song> findAll() { return jdbc.query("SELECT id,title,artist,album,genre,audio_url FROM songs ORDER BY id", mapper); }
 public List<Song> search(String q) {
  String term = "%" + q.trim().toLowerCase() + "%";
  return jdbc.query("SELECT id,title,artist,album,genre,audio_url FROM songs WHERE LOWER(title) LIKE ? OR LOWER(artist) LIKE ? OR LOWER(album) LIKE ? ORDER BY title", mapper, term, term, term);
 }
 public Optional<Song> findById(long id) {
  return jdbc.query("SELECT id,title,artist,album,genre,audio_url FROM songs WHERE id=?", mapper, id).stream().findFirst();
 }
 public Song create(Song s) {
  jdbc.update("INSERT INTO songs(title,artist,album,genre,audio_url) VALUES(?,?,?,?,?)", s.title(), s.artist(), s.album(), s.genre(), s.audioUrl());
  return jdbc.query("SELECT id,title,artist,album,genre,audio_url FROM songs ORDER BY id DESC LIMIT 1", mapper).stream().findFirst().orElseThrow();
 }
 public int delete(long id) { return jdbc.update("DELETE FROM songs WHERE id=?", id); }
}
