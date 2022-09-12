package com.example.backbase.service;

import com.example.backbase.data.MovieDetails;
import com.example.backbase.dto.MovieDTO;
import com.example.backbase.exception.MovieNotFoundException;
import com.example.backbase.mapper.MovieDtoToMovieMapper;
import com.example.backbase.repository.MovieDetailRepository;
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

    private MovieDtoToMovieMapper movieDtoToMovieMapper = Mappers.getMapper(MovieDtoToMovieMapper.class);

    @Override
    public List<MovieDetails> getBestOscarMovie() {
        MovieDetails movieDetails = new MovieDetails();
        logger.info("Fetching movie details by best picture ");
        List<MovieDetails> list = movieDetailRepository.getOscarWonMovie();
        logger.info("response:: ", list);
        return list;
    }

    @Override
    public MovieDetails postRatingToMovie(Long id, MovieDTO movieDTO) throws MovieNotFoundException {
        Optional<MovieDetails> movieDetail = movieDetailRepository.findById(id);
        if (!movieDetail.isPresent()) {
            throw new MovieNotFoundException("Movie not found for id " + id);
        }
        MovieDetails movieDetails = movieDtoToMovieMapper.map(movieDTO);
        movieDetail.get().setRating(movieDetails.getRating());
        return movieDetailRepository.save(movieDetail.get());
    }

    @Override
    public List<MovieDetails> findTopRatedMovie() {
        return movieDetailRepository.findTopRatedMovies();
    }


}
