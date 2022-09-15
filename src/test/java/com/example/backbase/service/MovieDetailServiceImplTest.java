package com.example.backbase.service;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.backbase.data.MovieDetails;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.repository.MovieDetailRepository;
import com.example.backbase.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
public class MovieDetailServiceImplTest {
    @InjectMocks
    MovieDetailServiceImpl movieDetailService;

    @Mock
    MovieDetailRepository movieDetailRepository;
    @Mock
    MovieRepository movieRepository;

    @BeforeAll
    public static void setUp() {
        // initMocks(this);
    }

    @Test
    void testGetBestOscarMovie() throws MovieNotFoundException {
        MovieDetails movieDetails = mock(MovieDetails.class);
        movieDetails.setYear("2018");
        movieDetails.setWon("YES");
        List<MovieDetails> list = new ArrayList<>();
        list.add(movieDetails);
        //when(movieDetailRepository.findByNomineeAndWonAndCategory("Amadeus", "YES", "Best Picture")).thenReturn(list);
        //List<MovieDetails> movieDetailsList = movieDetailService.getBestPictureWonOscar("Amadeus");
        //assertNotNull(movieDetailsList);
    }

  /*  @Test
    void testPostRatingToMovie() throws MovieNotFoundException {
        MovieDetails request = new MovieDetails();
        request.setWon("YES");
        request.setAcademyId(10);
        Movie movie = new Movie();
        movie.setMovieId(10L);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(10L);
        movieDTO.setRating(1L);
        when(movieRepository.findByMovieId(movieDTO.getMovieId())).thenReturn(movie);
        Movie response = movieDetailService.postRatingToMovie(10L, movieDTO);
        assertNotNull(response);
    }*/

  /*  @Test()
    void testPostRatingToMovieException() throws MovieNotFoundException {
        when(movieRepository.findByMovieId(any())).thenReturn(null);
        MovieNotFoundException thrown = assertThrows(
                MovieNotFoundException.class,
                () -> movieDetailService.postRatingToMovie(10L, new MovieDTO()),
                "Expected postRatingToMovie() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Movie not found for id"));

    }

    @Test
    void testPostRatingToMovieWith() throws MovieNotFoundException {
        MovieDetails request = new MovieDetails();
        request.setWon("YES");
        request.setAcademyId(10);
        Movie movie = new Movie();
        movie.setCount(1);
        movie.setMovieName("Amadeus");
        movie.setRating(1L);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(10L);
        movieDTO.setRating(1L);
        when(movieRepository.findByMovieId(movieDTO.getMovieId())).thenReturn(movie);
        Movie response = movieDetailService.postRatingToMovie(10L, movieDTO);
        assertNotNull(response);
    }

    @Test
    void testFindTopRatedMovie() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.setRating(3L);
        movie.setMovieId(1L);
        movieList.add(movie);
        when(movieRepository.getTopRatedMovies()).thenReturn(movieList);
        List<Movie> response = movieDetailService.findTopRatedMovie();
        assertNotNull(response);
    }*/
}
