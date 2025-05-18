package com.movieshub.controller;

import com.movieshub.model.Movie;
import com.movieshub.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie addedMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(addedMovie, HttpStatus.CREATED);
    }

    @PostMapping("/add/multiple")
    public ResponseEntity<List<Movie>> addMultipleMovies(@RequestBody List<Movie> movies) {
        List<Movie> addedMovies = movieService.addMultipleMovies(movies);
        return new ResponseEntity<>(addedMovies, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{imdbID}")
    public ResponseEntity<Void> removeMovie(@PathVariable String imdbID) {
        movieService.deleteMovie(imdbID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping("/remove/multiple")
    public ResponseEntity<Void> removeMultipleMovies(@RequestBody List<String> imdbIDs) {
        movieService.deleteMultipleMovies(imdbIDs);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{imdbID}")
    public ResponseEntity<Movie> getMovieByImdbID(@PathVariable String imdbID) {
        Optional<Movie> movie = movieService.getMovieByImdbID(imdbID);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(@RequestParam String query) {
        List<Movie> results = movieService.searchMoviesByTitle(query);
        return ResponseEntity.ok(results);
    }
}