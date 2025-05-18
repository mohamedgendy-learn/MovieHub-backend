package com.movieshub.controller; // Adjust the package name if needed

import com.movieshub.model.Rating;
import com.movieshub.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/submit")
    public ResponseEntity<Rating> submitRating(
            @RequestParam Long userId, // In a real app, you'd get this from the authentication context
            @RequestParam String movieId,
            @RequestParam Integer rating
    ) {
        Rating savedRating = ratingService.saveRating(userId, movieId, rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    @GetMapping("/movie/{movieId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable String movieId) {
        Double averageRating = ratingService.getAverageRatingForMovie(movieId);
        return ResponseEntity.ok(averageRating);
    }

    @GetMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<Rating> getUserRatingForMovie(
            @PathVariable Long userId,
            @PathVariable String movieId
    ) {
        return ratingService.getRatingByUserAndMovie(userId, movieId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}