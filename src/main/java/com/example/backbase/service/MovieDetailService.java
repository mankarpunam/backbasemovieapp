package com.example.backbase.service;

import java.util.List;

import com.example.backbase.data.MovieWithRatings;
import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
import com.example.backbase.exception.MovieNotFoundException;

public interface MovieDetailService {

	String getBestPictureWonOscar(String movieName) throws MovieNotFoundException;

	Rating postRatingToMovie(Long movie_id, RatingDTO ratingDTO) throws MovieNotFoundException;

	List<MovieWithRatings> findTopRatedMovie(Integer dataLimit);

}
