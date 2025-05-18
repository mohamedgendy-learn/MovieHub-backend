package com.movieshub.service;

import com.movieshub.model.Movie;
import com.movieshub.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> addMultipleMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    public void deleteMovie(String imdbID) {
        movieRepository.deleteById(imdbID);
    }

    @Transactional
    public void deleteMultipleMovies(List<String> imdbIDs) {
        imdbIDs.forEach(movieRepository::deleteById);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieByImdbID(String imdbID) {
        return movieRepository.findById(imdbID);
    }

    public List<Movie> searchMoviesByTitle(String query) {
        return movieRepository.findByTitleContainingIgnoreCase(query);
    }


}