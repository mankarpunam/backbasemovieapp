package com.example.backbase.mapper;

import com.example.backbase.data.Rating;
import com.example.backbase.dto.RatingDTO;
import lombok.Generated;
import org.mapstruct.Mapper;

@Mapper
@Generated
public interface RatingDtoToRatingMapper {
    public Rating map(RatingDTO ratingDTO);
}