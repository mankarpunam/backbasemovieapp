package com.example.backbase.controller;

import com.example.backbase.data.MovieDetails;
import com.example.backbase.dto.MovieDTO;
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
    public ResponseEntity<List<MovieDetails>> getBestPicture() {
        logger.info("In getBest picture api");
        List<MovieDetails> movieDetailsList = movieDetailService.getBestOscarMovie();
        return new ResponseEntity<>(movieDetailsList, HttpStatus.OK);
    }

    @PatchMapping("/rating/{id}")
    public ResponseEntity<MovieDetails> postRatingToMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) throws MovieNotFoundException {
        logger.info("In postRatingToMovie api ::post rating to movie");
        MovieDetails movieDetails = movieDetailService.postRatingToMovie(id, movieDTO);
        return new ResponseEntity<>(movieDetails, HttpStatus.OK);
    }

    @GetMapping("/topRatedMovies")
    public ResponseEntity<List<MovieDetails>> findTopRatedMovies() {
        logger.info("Fetching top rated movies by box office value ");
        return new ResponseEntity<>(movieDetailService.findTopRatedMovie(), HttpStatus.OK);
    }

}
