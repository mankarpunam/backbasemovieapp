package com.example.backbase.service;

import com.example.backbase.data.MovieDetails;
import com.example.backbase.dto.MovieDTO;
import com.example.backbase.exception.MovieNotFoundException;

import java.util.List;

public interface MovieDetailService {

    List<MovieDetails> getBetsOscarMovie();

    MovieDetails postRatingToMovie(Long id, MovieDTO movieDTO) throws MovieNotFoundException;

    List<MovieDetails> findTopRatedMovie();


}
