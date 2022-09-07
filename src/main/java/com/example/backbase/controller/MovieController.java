package com.example.backbase.controller;

import com.example.backbase.service.MovieDetailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private static final Logger logger = LogManager.getLogger(MovieController.class);
    @Autowired
    MovieDetailService movieDetailService;
    @GetMapping("/{bestPicture}")
    public ResponseEntity<String> getBestPicture() {
        logger.info("In getBest picture api");
        String movie = movieDetailService.getBetsOscarMovie();
        logger.info("Found account details by accountNumber {}");
        return new ResponseEntity<>("Best Oscar Award " + movie + " Winner",
                HttpStatus.OK);
    }
}
