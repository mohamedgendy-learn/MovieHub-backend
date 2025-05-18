package com.movieshub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "movieId"})
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // Identifier for the user who gave the rating

    @Column(nullable = false)
    private String movieId; // IMDb ID of the movie being rated

    @Column(nullable = false)
    private Integer rating; // The rating value (e.g., 1 to 5)

    // Constructors
    public Rating() {
    }

    public Rating(Long userId, String movieId, Integer rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", userId=" + userId +
                ", movieId='" + movieId + '\'' +
                ", rating=" + rating +
                '}';
    }
}