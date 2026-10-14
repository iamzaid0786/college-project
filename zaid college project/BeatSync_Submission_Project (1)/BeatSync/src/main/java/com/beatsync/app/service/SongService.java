package com.beatsync.app.service;
import com.beatsync.app.model.Song;
import com.beatsync.app.repository.SongRepository;
import org.springframework.stereotype.Service;
import java.util.List;
/** Business logic and input checks belong in the service layer. */
@Service
public class SongService {
 private final SongRepository repository;
 public SongService(SongRepository repository) { this.repository = repository; }
 public List<Song> getSongs(String query) { return query == null || query.isBlank() ? repository.findAll() : repository.search(query); }
 public Song addSong(Song song) {
  if (song == null || song.title() == null || song.title().isBlank()) throw new IllegalArgumentException("Song title is required.");
  if (song.artist() == null || song.artist().isBlank()) throw new IllegalArgumentException("Artist is required.");
  return repository.create(song);
 }
 public void deleteSong(long id) { if (repository.delete(id) == 0) throw new SongNotFoundException(id); }
}
