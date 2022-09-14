package com.example.backbase.mapper;

import com.example.backbase.data.Movie;
import com.example.backbase.dto.MovieDTO;
import lombok.Generated;
import org.mapstruct.Mapper;

@Mapper
@Generated
public interface MovieDtoToMovieMapper {
    public Movie map(MovieDTO movieDTO);
}
