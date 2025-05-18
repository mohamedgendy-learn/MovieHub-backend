package com.movieshub.repository;

import com.movieshub.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndMovieId(Long userId, String movieId);
    List<Rating> findByMovieId(String movieId);
}