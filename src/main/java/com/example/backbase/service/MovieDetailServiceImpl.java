package com.example.backbase.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backbase.data.Movie;
import com.example.backbase.data.MovieDetails;
import com.example.backbase.data.MovieWithRatings;
import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.repository.MovieDetailRepository;
import com.example.backbase.repository.MovieRepository;
import com.example.backbase.repository.RatingRepository;

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
	Long avgRate = 0L;
	int i = 0;

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
