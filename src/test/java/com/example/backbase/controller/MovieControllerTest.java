package com.example.backbase.controller;

import com.example.backbase.data.Movie;
import com.example.backbase.data.MovieDetails;
import com.example.backbase.data.MovieWithRatings;
import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.repository.MovieDetailRepository;
import com.example.backbase.repository.MovieRepository;
import com.example.backbase.service.MovieDetailService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@SpringBootTest(properties = "spring.profiles.active=test", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerTest {
    private static final long ID = 1L;
    @LocalServerPort
    int randomServerPort;

    @Mock
    private MovieDetailRepository movieDetailRepository;
    @Mock
    private MovieDetailService movieDetailService;
    @InjectMocks
    private MovieController movieController;
    @Mock
    private MovieRepository movieRepository;
    private static String BASE_URL;

    @BeforeAll
    public static void init() {
        BASE_URL = "http://localhost:";
    }

    @Test
    void testGetBestPicture() throws URISyntaxException, MovieNotFoundException {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setAcademyId(1);
        movieDetails.setYear("2018");
        movieDetails.setCategory("Directing");
        movieDetails.setNominee("The King's Speech");
        movieDetails.setWon("YES");
        movieDetails.setAdditionalInfo("Tom Hooper");
        List<MovieDetails> list = new ArrayList<>();
        list.add(movieDetails);
        when(movieDetailService.getBestPictureWonOscar("The King's Speech")).thenReturn("The movie True Grit has won the award!");
        when(movieDetailRepository.findFirstByNomineeAndCategory("he King's Speech", "Best Picture")).thenReturn(movieDetails);
        ResponseEntity<String> responseEntity = movieController.getBestPictureWonOscar("The King's Speech");
        assertNotNull(responseEntity);

    }

    @Test()
    void testPostRatingToMovie() throws MovieNotFoundException {
        Movie movie = new Movie();
        movie.setMovieId(10L);
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRating(10L);
        Rating rating = new Rating();
        rating.setRating(4L);
        rating.setMovieId(1L);
        when(movieRepository.findByMovieId(10L)).thenReturn(movie);
        when(movieDetailService.postRatingToMovie(10L, ratingDTO)).thenReturn(rating);
        ResponseEntity<Rating> responseEntity = movieController.postRatingToMovie(10L, ratingDTO);
        assertNotNull(responseEntity);
    }

    @Test()
    void testFindTopRatedMovies() throws MovieNotFoundException {
        when(movieDetailService.findTopRatedMovie(10)).thenReturn(new ArrayList<>());
        ResponseEntity<List<MovieWithRatings>> responseEntity = movieController.findTopRatedMovies(10);
        assertNotNull(responseEntity);
    }
}
