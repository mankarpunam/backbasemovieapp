package com.example.backbase.controller;

import com.example.backbase.data.MovieDetails;
import com.example.backbase.dto.MovieDTO;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.service.MovieDetailService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@SpringBootTest(properties = "spring.profiles.active=test", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTest {
    private static final long ID = 1L;
    @LocalServerPort
    int randomServerPort;

    @Mock
    private MovieDetailService movieDetailService;
    @Autowired
    private MovieController movieController;
    private static String BASE_URL;

    @BeforeAll
    public static void init() {
        BASE_URL = "http://localhost:";
    }

    @Test
    void testGetBestPicture() throws URISyntaxException {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setId(1L);
        movieDetails.setYear("2018");
        movieDetails.setCategory("Directing");
        movieDetails.setNominee("The King's Speech");
        movieDetails.setRating(10);
        movieDetails.setWon("YES");
        movieDetails.setAdditional_info("Tom Hooper");
        List<MovieDetails> list = new ArrayList<>();
        list.add(movieDetails);
        when(movieDetailService.getBestOscarMovie()).thenReturn(list);
        ResponseEntity<List<MovieDetails>> responseEntity = movieController.getBestPicture();
        assertNotNull(responseEntity);

    }

    @Test()
    void testPostRatingToMovie() throws MovieNotFoundException {
        when(movieDetailService.postRatingToMovie(10L, new MovieDTO())).thenReturn(new MovieDetails());
        ResponseEntity<MovieDetails> responseEntity = movieController.postRatingToMovie(10L, new MovieDTO());
        assertNotNull(responseEntity);
    }

 @Test()
    void testPostRatingToMovieWithException() throws MovieNotFoundException {
        when(movieDetailService.postRatingToMovie(null, null)).thenReturn(null);
        MovieNotFoundException thrown = assertThrows(
                MovieNotFoundException.class,
                () -> movieController.postRatingToMovie(anyLong(), any()),
                "Expected postRatingToMovie() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Movie not found for id"));

    }


    @Test()
    void testFindTopRatedMovies() throws MovieNotFoundException {
        when(movieDetailService.findTopRatedMovie()).thenReturn(new ArrayList<>());
        ResponseEntity<List<MovieDetails>> responseEntity = movieController.findTopRatedMovies();
        assertNotNull(responseEntity);
    }
}
