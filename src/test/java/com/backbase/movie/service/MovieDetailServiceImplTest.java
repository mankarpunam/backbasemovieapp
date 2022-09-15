package com.backbase.movie.service;

import com.backbase.movie.data.Movie;
import com.backbase.movie.data.MovieDetails;
import com.backbase.movie.data.MovieWithRatings;
import com.backbase.movie.data.Rating;
import com.backbase.movie.dto.RatingDTO;
import com.backbase.movie.exception.MovieNotFoundException;
import com.backbase.movie.repository.MovieDetailRepository;
import com.backbase.movie.repository.MovieRepository;
import com.backbase.movie.repository.RatingRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieDetailServiceImplTest {
    @InjectMocks
    MovieDetailServiceImpl movieDetailService;

    @Mock
    MovieDetailRepository movieDetailRepository;
    @Mock
    MovieRepository movieRepository;

    @Mock
    RatingRepository ratingRepository;

    @BeforeAll
    public static void setUp() {
        // initMocks(this);
    }

    @Test
    void testGetBestOscarMovie() throws MovieNotFoundException {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setYear("2018");
        movieDetails.setWon("YES");
        List<MovieDetails> list = new ArrayList<>();
        list.add(movieDetails);
        when(movieDetailRepository.findFirstByNomineeAndCategory("Amadeus", "Best Picture")).thenReturn(movieDetails);
        String msg = movieDetailService.getBestPictureWonOscar("Amadeus");
        assertEquals(msg, "The movie Amadeus has won the award!");
    }

    @Test
    void testGetBestOscarMovieError() throws MovieNotFoundException {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setYear("2018");
        movieDetails.setWon("YES");
        List<MovieDetails> list = new ArrayList<>();
        list.add(movieDetails);
        when(movieDetailRepository.findFirstByNomineeAndCategory("Amadeus", "Best Picture")).thenReturn(null);
        String msg = movieDetailService.getBestPictureWonOscar("Amadeus");
        assertEquals(msg, "The movie Amadeus is not found in our database. Please check the name agian!");
    }

    @Test
    void testPostRatingToMovie() throws MovieNotFoundException {
        MovieDetails request = new MovieDetails();
        request.setWon("YES");
        request.setAcademyId(10);
        Movie movie = new Movie();
        movie.setMovieId(10L);
        Rating rating = new Rating();
        rating.setRatingId(2L);
        rating.setRatingId(1L);
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setRating(2L);
        when(movieRepository.findById(movie.getMovieId())).thenReturn(Optional.of(movie));
        Rating rating1 = movieDetailService.postRatingToMovie(10L, ratingDTO);
        verify(movieRepository, atLeastOnce()).findById(10L);
    }

    @Test()
    void testPostRatingToMovieException() throws MovieNotFoundException {
        when(movieRepository.findById(any())).thenReturn(Optional.empty());
        MovieNotFoundException thrown = assertThrows(
                MovieNotFoundException.class,
                () -> movieDetailService.postRatingToMovie(10L, new RatingDTO()),
                "Expected postRatingToMovie() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Movie not found for id"));

    }

    @Test
    void testFindTopRatedMovie() {
        List<MovieWithRatings> movieWithRatings = new ArrayList<>();
        MovieWithRatings movie = new MovieWithRatings();
        movie.setMovieName("Avenger");
        movie.setMovieId(1L);
        movieWithRatings.add(movie);
        when(movieRepository.getTopRatedMovies(10)).thenReturn(movieWithRatings);
        List<MovieWithRatings> response = movieDetailService.findTopRatedMovie(10);
        assertNotNull(response);
    }
}
