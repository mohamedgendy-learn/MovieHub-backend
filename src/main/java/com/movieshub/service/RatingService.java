package com.movieshub.service; // Adjust the package name if needed

import com.movieshub.model.Rating;
import com.movieshub.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating saveRating(Long userId, String movieId, Integer ratingValue) {
        Optional<Rating> existingRating = ratingRepository.findByUserIdAndMovieId(userId, movieId);
        Rating rating;
        if (existingRating.isPresent()) {
            rating = existingRating.get();
            rating.setRating(ratingValue);
        } else {
            rating = new Rating(userId, movieId, ratingValue);
        }
        return ratingRepository.save(rating);
    }

    public Optional<Rating> getRatingByUserAndMovie(Long userId, String movieId) {
        return ratingRepository.findByUserIdAndMovieId(userId, movieId);
    }

    public List<Rating> getRatingsByMovie(String movieId) {
        return ratingRepository.findByMovieId(movieId);
    }

    public Double getAverageRatingForMovie(String movieId) {
        List<Rating> ratings = ratingRepository.findByMovieId(movieId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRating();
        }
        return sum / ratings.size();
    }
}