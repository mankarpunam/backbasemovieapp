package com.example.backbase.mapper;

import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
import org.mapstruct.Mapper;

@Mapper
public abstract class RatingDtoToRatingMapper {
    public abstract Rating map(RatingDTO ratingDTO);
}
