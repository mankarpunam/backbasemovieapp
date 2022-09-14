package com.example.backbase.service;

import com.example.backbase.data.Movie;
import com.example.backbase.data.MovieDetails;
import com.example.backbase.dto.MovieDTO;
import com.example.backbase.exception.MovieException;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.mapper.MovieDtoToMovieMapper;
import com.example.backbase.repository.MovieDetailRepository;
import com.example.backbase.repository.MovieRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDetailServiceImpl implements MovieDetailService {
    private static final Logger logger = LogManager.getLogger(MovieDetailServiceImpl.class);
    @Autowired
    MovieDetailRepository movieDetailRepository;

    @Autowired
    MovieRepository movieRepository;

    private MovieDtoToMovieMapper movieDtoToMovieMapper = Mappers.getMapper(MovieDtoToMovieMapper.class);

    private static final String YES = "YES";
    private static final String CATEGORY = "Best Picture";
    Long avgRate = 0L;
    int i = 0;

    @Override
    public List<MovieDetails> getBestPictureWonOscar(String movieName) throws MovieNotFoundException {
        logger.info("Fetching movie details by best picture ");
        List<MovieDetails> movieDetailsList = movieDetailRepository.findByNomineeAndWonAndCategory(movieName, YES, CATEGORY);
        logger.info("Fetching best picture which won oscar:{}", movieDetailsList);
        if (movieDetailsList.isEmpty()) {
            throw new MovieException("Movie Not Found in table: not valid movie :" + movieName);
        }
        return movieDetailsList;
    }

    @Override
    public Movie postRatingToMovie(Long movieId, MovieDTO movieDTO) throws MovieNotFoundException {
        logger.info("In MovieDetailServiceImpl post rating to movies");
        Movie movieFromDb = movieRepository.findByMovieId(movieId);
        if (movieFromDb == null) {
            throw new MovieNotFoundException("Movie not found for id " + movieId);
        }
        if (movieFromDb.getCount() == null) {
            avgRate = movieDTO.getRating();
            movieDTO.setRating(avgRate);
            movieDTO.setMovieId(movieFromDb.getMovieId());
            movieDTO.setMovieName(movieFromDb.getMovieName());
            movieDTO.setCount(++i);

        } else {
            int count = movieFromDb.getCount();
            movieDTO.setCount(++count);
            avgRate = (movieFromDb.getRating() + movieDTO.getRating()) / movieDTO.getCount();
            movieDTO.setRating(avgRate);
            movieDTO.setMovieId(movieFromDb.getMovieId());
            movieDTO.setMovieName(movieFromDb.getMovieName());
        }
        Movie movie = movieDtoToMovieMapper.map(movieDTO);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public List<Movie> findTopRatedMovie() {
        List<Movie> movieList = movieRepository.getTopRatedMovies();
        return movieList;
    }
}
