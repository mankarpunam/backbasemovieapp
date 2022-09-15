package com.backbase.movie.service;

import java.util.List;
import java.util.Optional;

import com.backbase.movie.data.MovieWithRatings;
import com.backbase.movie.dto.RatingDTO;
import com.backbase.movie.exception.MovieNotFoundException;
import com.backbase.movie.repository.MovieDetailRepository;
import com.backbase.movie.repository.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backbase.movie.data.Movie;
import com.backbase.movie.data.MovieDetails;
import com.backbase.movie.data.Rating;
import com.backbase.movie.repository.MovieRepository;

@Service
public class MovieDetailServiceImpl implements MovieDetailService {
	private static final Logger logger = LogManager.getLogger(MovieDetailServiceImpl.class);
	@Autowired
	MovieDetailRepository movieDetailRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	RatingRepository ratingRepository;

	private static final String YES = "YES";
	private static final String CATEGORY = "Best Picture";

	@Override
	public String getBestPictureWonOscar(String movieName) throws MovieNotFoundException {
		logger.info("Fetching movie details by best picture ");
		MovieDetails movieDetails = movieDetailRepository.findFirstByNomineeAndCategory(movieName, CATEGORY);
		logger.info("Fetching best picture which won oscar:{}", movieDetails);
		StringBuilder stringBuilder = new StringBuilder("The movie ");
		stringBuilder.append(movieName);
		if (movieDetails == null) {
			stringBuilder.append(" is not found in our database. Please check the name agian!");
		} else {
			stringBuilder.append(" has ");
			stringBuilder.append(movieDetails.getWon().equals(YES) ? "won " : "not won ");
			stringBuilder.append("the award!");
		}

		return stringBuilder.toString();
	}

	public Rating postRatingToMovie(Long movieId, RatingDTO ratingDTO) throws MovieNotFoundException {
		logger.info("In MovieDetailServiceImpl post rating to movies");
		Optional<Movie> movie = movieRepository.findById(movieId);
		if (!movie.isPresent()) {
			throw new MovieNotFoundException("Movie not found for id " + movieId);
		}

		Rating rating = new Rating();
		rating.setRating(ratingDTO.getRating());
		rating.setMovieId(movieId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<MovieWithRatings> findTopRatedMovie(Integer dataLimit) {
		List<MovieWithRatings> ratingList = movieRepository.getTopRatedMovies(dataLimit);
		return ratingList;
	}

}
