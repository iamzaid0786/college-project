package com.beatsync.app.controller;
import com.beatsync.app.service.SongNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
 public Map<String,String> badRequest(IllegalArgumentException ex) { return Map.of("error", ex.getMessage()); }
 @ExceptionHandler(SongNotFoundException.class) @ResponseStatus(HttpStatus.NOT_FOUND)
 public Map<String,String> notFound(SongNotFoundException ex) { return Map.of("error", ex.getMessage()); }
}
