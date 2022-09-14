package com.example.backbase.service;

import com.example.backbase.data.Movie;
import com.example.backbase.data.MovieDetails;
import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
import com.example.backbase.exception.MovieException;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.mapper.MovieDtoToMovieMapper;
import com.example.backbase.mapper.RatingDtoToRatingMapper;
import com.example.backbase.repository.MovieDetailRepository;
import com.example.backbase.repository.MovieRepository;
import com.example.backbase.repository.RatingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieDetailServiceImpl implements MovieDetailService {
    private static final Logger logger = LogManager.getLogger(MovieDetailServiceImpl.class);
    @Autowired
    MovieDetailRepository movieDetailRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RatingRepository ratingRepository;

    private MovieDtoToMovieMapper movieDtoToMovieMapper = Mappers.getMapper(MovieDtoToMovieMapper.class);
    private RatingDtoToRatingMapper ratingDtoToRatingMapper = Mappers.getMapper(RatingDtoToRatingMapper.class);

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

   /* @Override
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
    }*/

    public Rating postRatingToMovie(Long movieId, RatingDTO ratingDTO) throws MovieNotFoundException {
        logger.info("In MovieDetailServiceImpl post rating to movies");
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()) {
            throw new MovieNotFoundException("Movie not found for id " + movieId);
        }
        List<Rating> ratingFromDb = ratingRepository.findByMovieId(movie.get().getMovieId());
        Rating rating = new Rating();
        if (!ratingFromDb.isEmpty()) {
            ratingFromDb.forEach(obj -> {
                if (obj.getMovieId().equals(movie.get().getMovieId())) {
                    int count = obj.getCount(); //sadhya rahu dya liquibase script change kare aprynt
                    ratingDTO.setCount(++count);
                    rating.setCount(++count);
                    rating.setRating(ratingDTO.getRating());
                    rating.setMovieId(obj.getMovieId());
                }
            });
        } else {
            rating.setRating(ratingDTO.getRating());
            rating.setMovieId(movieId);
            rating.setCount(++i);
        }
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> findTopRatedMovie() {
        List<Rating> ratingList = ratingRepository.getTopRatedMovies();
        return ratingList;
    }

}
