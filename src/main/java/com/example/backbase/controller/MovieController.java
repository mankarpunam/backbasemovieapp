package com.example.backbase.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backbase.data.MovieWithRatings;
import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.service.MovieDetailService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	private static final Logger logger = LogManager.getLogger(MovieController.class);
	@Autowired
	MovieDetailService movieDetailService;

	@GetMapping("/bestPicture")
	public ResponseEntity<String> getBestPictureWonOscar(@RequestParam(value = "title") String title)
			throws MovieNotFoundException {
		logger.info("In getBest picture api");
		return new ResponseEntity<>(movieDetailService.getBestPictureWonOscar(title), HttpStatus.OK);
	}

	@PutMapping("/rating/{movie_id}")
	public ResponseEntity<Rating> postRatingToMovie(@PathVariable Long movie_id,
			@RequestBody @Valid RatingDTO ratingDTO) throws MovieNotFoundException {
		logger.info("In postRatingToMovie api ::post rating to movie");
		Rating response = movieDetailService.postRatingToMovie(movie_id, ratingDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/topRatedMovies")
	public ResponseEntity<List<MovieWithRatings>> findTopRatedMovies(@RequestParam("dataLimit")Integer dataLimit) {
		logger.info("Fetching top rated movies by box office value ");
		return new ResponseEntity<>(movieDetailService.findTopRatedMovie(dataLimit), HttpStatus.OK);
	}
}
