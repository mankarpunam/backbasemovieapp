package com.backbase.movie.mapper;

import com.backbase.movie.dto.MovieDTO;
import com.backbase.movie.data.Movie;
import lombok.Generated;
import org.mapstruct.Mapper;

@Mapper
@Generated
public interface MovieDtoToMovieMapper {
    public Movie map(MovieDTO movieDTO);
}
