package com.example.backbase.service;

import com.example.backbase.data.Movie;
import com.example.backbase.data.MovieDetails;
import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
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
        logger.info("Fetching best picture which win oscar:{}", movieDetailsList);
        return movieDetailsList;
    }

    @Override
    public Rating postRatingToMovie(Long movieId, RatingDTO ratingDTO) throws MovieNotFoundException {
        logger.info("In MovieDetailServiceImpl post rating to movies");
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()) {
            throw new MovieNotFoundException("Movie not found for id " + movieId);
        }
        List<Rating> ratingFromDb = ratingRepository.findByMovieId(movie.get().getMovieId());

        if (!ratingFromDb.isEmpty()) {
            ratingFromDb.forEach(obj -> {
                if (obj.getMovieId().equals(movie.get().getMovieId())) {
                    int count = obj.getCount();
                    ratingDTO.setCount(++count);
                    avgRate = ((obj.getRating() + ratingDTO.getRating()) / ratingDTO.getCount());
                    ratingDTO.setRating(avgRate);
                    ratingDTO.setMovieId(obj.getMovieId());
                    ratingDTO.setRatingId(obj.getRatingId());
                    Rating rating = ratingDtoToRatingMapper.map(ratingDTO);
                    ratingRepository.save(rating);
                }
            });
        } else {
            avgRate = ratingDTO.getRating();
            ratingDTO.setRating(avgRate);
            ratingDTO.setMovieId(movie.get().getMovieId());
            ratingDTO.setCount(++i);
            Rating rating = ratingDtoToRatingMapper.map(ratingDTO);
            ratingRepository.save(rating);
            return rating;
        }
        return null;
    }


    @Override
    public List<Rating> findTopRatedMovie() {
        List<Rating> ratingList = ratingRepository.getTopRatedMovies();
        return ratingList;
    }


}
