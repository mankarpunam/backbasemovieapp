package com.example.backbase.service;

import com.example.backbase.data.Movie;
import com.example.backbase.data.MovieDetails;
import com.example.backbase.data.Rating;
import com.example.backbase.dto.MovieDTO;
import com.example.backbase.dto.RatingDTO;
import com.example.backbase.exception.MovieNotFoundException;

import java.util.List;

public interface MovieDetailService {

    List<MovieDetails> getBestPictureWonOscar(String movieName) throws MovieNotFoundException;

  /*  Movie postRatingToMovie(Long movie_id, MovieDTO movieDTO) throws MovieNotFoundException;

    List<Movie> findTopRatedMovie();*/

    Rating postRatingToMovie(Long movie_id, RatingDTO ratingDTO) throws MovieNotFoundException;
    List<Rating> findTopRatedMovie();

}
