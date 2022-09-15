package com.backbase.movie.service;

import java.util.List;

import com.backbase.movie.data.MovieWithRatings;
import com.backbase.movie.data.Rating;
import com.backbase.movie.dto.RatingDTO;
import com.backbase.movie.exception.MovieNotFoundException;

public interface MovieDetailService {

	String getBestPictureWonOscar(String movieName) throws MovieNotFoundException;

	Rating postRatingToMovie(Long movie_id, RatingDTO ratingDTO) throws MovieNotFoundException;

	List<MovieWithRatings> findTopRatedMovie(Integer dataLimit);

}
