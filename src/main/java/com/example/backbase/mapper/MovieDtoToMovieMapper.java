package com.example.backbase.mapper;

import com.example.backbase.data.Movie;
import com.example.backbase.data.MovieDetails;
import com.example.backbase.dto.MovieDTO;
import com.example.backbase.dto.MovieDetailDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MovieDtoToMovieMapper {
    public MovieDetails map(MovieDetailDTO movieDetailDTO);
    public Movie map(MovieDTO movieDTO);
}
