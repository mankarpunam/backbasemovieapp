package com.example.backbase.controller;

import com.example.backbase.data.MovieDetails;
import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.service.MovieDetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private static final Logger logger = LogManager.getLogger(MovieController.class);
    @Autowired
    MovieDetailService movieDetailService;

    @GetMapping("/bestPicture")
    public ResponseEntity<List<MovieDetails>> getBestPictureWonOscar(@RequestParam(value = "title") String title) throws MovieNotFoundException {
        logger.info("In getBest picture api");
        return new ResponseEntity<>(movieDetailService.getBestPictureWonOscar(title), HttpStatus.OK);
    }

    /*@PutMapping("/rating/{movie_id}")
    public ResponseEntity<Movie> postRatingToMovie(@PathVariable Long movie_id, @RequestBody MovieDTO movieDTO) throws MovieNotFoundException {
        logger.info("In postRatingToMovie api ::post rating to movie");
        Movie response = movieDetailService.postRatingToMovie(movie_id, movieDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    /*@GetMapping("/topRatedMovies")
    public ResponseEntity<List<Movie>> findTopRatedMovies() {
        logger.info("Fetching top rated movies by box office value ");
        return new ResponseEntity<>(movieDetailService.findTopRatedMovie(), HttpStatus.OK);
    }*/
    @PutMapping("/rating/{movie_id}")
    public ResponseEntity<Rating> postRatingToMovie(@PathVariable Long movie_id, @RequestBody RatingDTO ratingDTO) throws MovieNotFoundException {
        logger.info("In postRatingToMovie api ::post rating to movie");
        Rating response = movieDetailService.postRatingToMovie(movie_id, ratingDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/topRatedMovies")
    public ResponseEntity<List<Rating>> findTopRatedMovies() {
        logger.info("Fetching top rated movies by box office value ");
        return new ResponseEntity<>(movieDetailService.findTopRatedMovie(), HttpStatus.OK);
    }
}
