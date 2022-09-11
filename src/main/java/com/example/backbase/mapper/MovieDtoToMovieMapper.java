package com.example.backbase.mapper;

import com.example.backbase.data.MovieDetails;
import com.example.backbase.dto.MovieDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MovieDtoToMovieMapper {
    public MovieDetails map(MovieDTO movieDTO);
}
