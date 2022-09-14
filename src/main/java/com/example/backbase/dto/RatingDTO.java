package com.example.backbase.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
@Generated
@EqualsAndHashCode
public class RatingDTO {
    private Long ratingId;
    private Long movieId;
    @Min(0)
    @Max(5)
    private Long rating;
    private Integer count;
}
