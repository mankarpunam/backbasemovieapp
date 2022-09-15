package com.backbase.movie.mapper;

import com.backbase.movie.data.Rating;
import com.backbase.movie.dto.RatingDTO;
import lombok.Generated;
import org.mapstruct.Mapper;

@Mapper
@Generated
public interface RatingDtoToRatingMapper {
    public Rating map(RatingDTO ratingDTO);
}