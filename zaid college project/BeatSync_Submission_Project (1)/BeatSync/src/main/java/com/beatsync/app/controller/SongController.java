package com.beatsync.app.controller;
import com.beatsync.app.model.Song;
import com.beatsync.app.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/** Spring MVC endpoints run on the Servlet web stack and exchange JSON. */
@RestController
@RequestMapping("/api/songs")
public class SongController {
 private final SongService service;
 public SongController(SongService service) { this.service = service; }
 @GetMapping public List<Song> list(@RequestParam(required=false) String q) { return service.getSongs(q); }
 @PostMapping @ResponseStatus(HttpStatus.CREATED)
 public Song create(@RequestBody SongRequest request) {
  return service.addSong(new Song(null, request.title(), request.artist(), request.album(), request.genre(), request.audioUrl()));
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
 public void delete(@PathVariable long id) { service.deleteSong(id); }
 public record SongRequest(String title, String artist, String album, String genre, String audioUrl) {}
}
